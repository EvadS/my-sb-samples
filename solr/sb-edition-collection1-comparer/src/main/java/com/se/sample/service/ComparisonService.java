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
import java.util.stream.Collectors;

@Service
public class ComparisonService {

    private final SolrClient upsuSolrClient;

    private static final Logger logger = LoggerFactory.getLogger(ComparisonService.class);
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
    private void init(){

        try {
            List<String> iterate = iterateSolrDocuments("");
            int a =0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  List<String> iterate() throws SolrServerException, IOException {
        List<String> notExistedEdition = new ArrayList<>();
        SolrQuery query = new SolrQuery("*:*");
        query.setRows(1000); // Размер пакета (batch size)

        query.addField(FieldMapping.id);
        query.addField(FieldMapping.f_current_edition);
        query.addField(FieldMapping.f_edition_list_ids);


        upsuSolrClient.queryAndStreamResponse(collectionName, query, new StreamingResponseCallback() {
            @Override
            public void streamSolrDocument(SolrDocument doc) {
                String id = doc.getFieldValue(FieldMapping.id).toString();
                Object editionListObj = doc.getFieldValue(FieldMapping.f_edition_list_ids);
                // Конвертация значения поля в List<String>
                List<String> editionList = objectToStringList(editionListObj);
                // Обработка каждого документа по мере поступления
                logger.info("id:{} , editions: {}", id, editionList);

                List<String> editionIds = buildEditionIds(id,  editionList);

                notExistedEdition.addAll(getNotExistedEdition(editionIds));

            }

            @Override
            public void streamDocListInfo(long numFound, long start, Float maxScore) {
                // Вызывается один раз перед началом потока с метаданными
                System.out.println("Всего найдено: " + numFound);
            }
        });

        return notExistedEdition;
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
            if(StringUtils.isEmpty(edition)){
                editionIds.add(id);
            }
            else{
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

    public List<String> iterateSolrDocuments(String query) throws Exception {
        List<String> notExistedEdition = new ArrayList<>();
        // Build the query targeting all documents
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*"); // Match all documents
        solrQuery.setRows(500);    // Fetch data in chunks of 500

        // Cursors REQUIRE a tie-breaking sort field (like 'id' or your unique key)
        solrQuery.setSort("id", SolrQuery.ORDER.asc);

        // Start from the beginning
        String cursorMark = CursorMarkParams.CURSOR_MARK_START;
        boolean done = false;

        while (!done) {
            solrQuery.set(CursorMarkParams.CURSOR_MARK_PARAM, cursorMark);
            solrQuery.addField(FieldMapping.id);
            solrQuery.addField(FieldMapping.f_current_edition);
            solrQuery.addField(FieldMapping.f_edition_list_ids);

            QueryResponse response = upsuSolrClient.query(collectionName, solrQuery);
            SolrDocumentList documents = response.getResults();

            // Iterate over the documents in the current batch
            for (SolrDocument doc : documents) {
                String id = doc.getFieldValue(FieldMapping.id).toString();
                logger.info("Processing doc, id: {}", id);
                Object editionListObj = doc.getFieldValue(FieldMapping.f_edition_list_ids);
                // Конвертация значения поля в List<String>
                List<String> editionList = objectToStringList(editionListObj);
                // Обработка каждого документа по мере поступления
                logger.info("id:{} , editions: {}", id, editionList);

                List<String> editionIds = buildEditionIds(id,  editionList);

                notExistedEdition.addAll(getNotExistedEdition(editionIds));

            }

            // Get the cursor mark for the next page
            String nextCursorMark = response.getNextCursorMark();

            // If the cursor mark doesn't change, we have reached the end
            if (cursorMark.equals(nextCursorMark)) {
                done = true;
            }

            cursorMark = nextCursorMark;
        }

        upsuSolrClient.close();
        logger.info("Finished iterating all documents.");

        return  notExistedEdition;
    }
}
