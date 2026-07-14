package com.se.sample.service;

import com.se.sample.config.FieldMapping;
import jakarta.annotation.PostConstruct;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.StreamingResponseCallback;
import org.apache.solr.client.solrj.impl.HttpJdkSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CursorMarkParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ComparisonService {

    private static final Logger logger = LoggerFactory.getLogger(ComparisonService.class);
    private final SolrClient upsuSolrClient;
    private final String collectionName;
    private final String editionName;


    public ComparisonService(@Qualifier("ipsuSolrClient") SolrClient solrClient,
                             @Value("${data.solr.core.collection:collection1}") String collection1Name,
                             @Value("${data.solr.core.edtions:editions}") String editionCollectionName) {
        this.upsuSolrClient = solrClient;
        this.collectionName = collection1Name;
        this.editionName = editionCollectionName;

        logger.info("******************   Result summary repository initialization     ***************");
        logger.info("collection1, name          : {}", collection1Name);
        logger.info("editionCollectionName, url : {}", editionCollectionName);
        logger.info("solrClient url             : {}", ((HttpJdkSolrClient) this.upsuSolrClient).getBaseURL());

        try {
            SolrPingResponse pingCollection1 = this.upsuSolrClient.ping(collection1Name);
            SolrPingResponse pingEdition = this.upsuSolrClient.ping(editionCollectionName);

            logger.info("solrClient ping {}, response :  {}", this.collectionName, pingCollection1.getResponse());
            logger.info("solrClient ping {} , response:  {}", this.editionName, pingEdition.getResponse());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    private void init() {
        List<String> lostEditionsList = getLostEditionsList("");
        int aaa = 0;
    }

    private List<String> getNotExistedEdition(List<String> editionIds) {
        List<String> notExistedEdition = new ArrayList<>();
        for (String editionId : editionIds) {
            SolrQuery query = new SolrQuery();
            query.setQuery(String.format("%s:\"%s\"", FieldMapping.id, editionId));
            query.setRows(0); // Нам не нужны документы, только количество

            try {
                long count = upsuSolrClient.query(editionName, query).getResults().getNumFound();
                if (count == 0) {
                    logger.info("edition {} not found", editionId);
                    notExistedEdition.add(editionId);
                }
            } catch (SolrServerException | IOException e) {
                logger.error("Ошибка при проверке существования издания {}: {}", editionId, e.getMessage());
            }
        }
        return notExistedEdition;

    }

    private List<String> buildEditionIds(String id, List<String> editionList) {
        List<String> editionIds = new ArrayList<>();
        for (String edition : editionList) {
            if (StringUtils.isEmpty(edition)) {
                editionIds.add(id);
            } else {
                editionIds.add(String.format("%s_%s", id, edition)); // В данном случае просто добавляем строку как есть
            }
        }
        return editionIds;
    }

    private List<String> objectToStringList(Object listObj) {
        if (listObj instanceof Collection) {
            return ((Collection<?>) listObj).stream()
                    .map(Objects::toString)
                    .collect(Collectors.toList());
        } else if (listObj != null) {
            return Collections.singletonList(listObj.toString());
        } else {
            return Collections.emptyList();
        }
    }

    // Extracted helper to process a single SolrDocument: build edition ids and check for missing editions
    private List<String> processDocument(SolrDocument doc) {
        try {
            String id = doc.getFieldValue(FieldMapping.id).toString();
            logger.info("Processing doc (thread: {}), id: {}", Thread.currentThread().getName(), id);
            Object editionListObj = doc.getFieldValue(FieldMapping.f_edition_list_ids);
            List<String> editionList = objectToStringList(editionListObj);
            logger.info("id:{} , editions: {}", id, editionList);
            List<String> editionIds = buildEditionIds(id, editionList);
            return getNotExistedEdition(editionIds);
        } catch (Exception e) {
            logger.error("Error processing document: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<String> getLostEditionsList(String query) {
        List<String> notExistedEdition = new ArrayList<>();
        // Build the query targeting all documents
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setRows(500);
        if (!StringUtils.isEmpty(query)) {
            logger.info("Using); custom query: {}", query);
            solrQuery.setQuery(query);
        }

        solrQuery.setSort("id", SolrQuery.ORDER.asc);
        solrQuery.addField(FieldMapping.id);
        solrQuery.addField(FieldMapping.f_current_edition);
        solrQuery.addField(FieldMapping.f_edition_list_ids);
        // Start from the beginning
        String cursorMark = CursorMarkParams.CURSOR_MARK_START;
        boolean done = false;

        try {
            while (!done) {
                solrQuery.set(CursorMarkParams.CURSOR_MARK_PARAM, cursorMark);
                try {
                    QueryResponse response = upsuSolrClient.query(collectionName, solrQuery);
                    SolrDocumentList documents = response.getResults();

                    // For each document build edition ids and check existence
                    for (SolrDocument doc : documents) {
                        notExistedEdition.addAll(processDocument(doc));
                    }

                    // Get the cursor mark for the next page
                    String nextCursorMark = response.getNextCursorMark();

                    // If the cursor mark doesn't change, we have reached the end
                    if (cursorMark.equals(nextCursorMark)) {
                        done = true;
                    }

                    cursorMark = nextCursorMark;
                } catch (SolrServerException | IOException e) {
                    logger.error("Error processing document in parallel: {}", e.getMessage(), e);
                }
            }

            logger.info("Finished iterating all documents (multithreaded). Collected {} missing editions.", notExistedEdition.size());

            return notExistedEdition;
        } finally {
            try {
                upsuSolrClient.close();
            } catch (IOException e) {
                logger.warn("Error closing Solr client: {}", e.getMessage(), e);
            }
        }
    }
}
