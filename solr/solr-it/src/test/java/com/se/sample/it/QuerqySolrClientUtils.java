package com.se.sample.it;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;

import org.testcontainers.containers.SolrClientUtils;
//import querqy.rewrite.commonrules.WhiteSpaceQuerqyParserFactory;
//import querqy.rewrite.lookup.preprocessing.LookupPreprocessorType;
//import querqy.solr.RewriterConfigRequestBuilder;
//import querqy.solr.RewriterConfigRequestBuilder.SaveRewriterConfigSolrResponse;
//import querqy.solr.rewriter.commonrules.CommonRulesConfigRequestBuilder;
//import querqy.solr.rewriter.replace.ReplaceConfigRequestBuilder;
//import querqy.solr.rewriter.wordbreak.WordBreakCompoundConfigRequestBuilder;

/**
 * Mostly copied code from {@link SolrClientUtils} as it's not open for
 * extension :-/
 */
public class QuerqySolrClientUtils extends SolrClientUtils {

    /**
     *
     * @param collectionName    the name of the collection which should be created
     * @param configurationName the name of the configuration which should used to
     *                          create the collection or null if the default
     *                          configuration should be used
     * @param numShards         the number of shards in the new collection
     *
     * @see SolrClientUtils
     */
    public static void createCollection(QuerqySolrContainer solr, String collectionName, String configurationName,
                                        int numShards) {

        System.out.printf("solr.url: %s%n", solr.getSolrUrl());
        // compose create collection url
        HttpGet createCollection = new HttpGet(String.format(
                "%s/admin/collections?action=CREATE&name=%s&numShards=%s&replicationFactor=1&wt=json&collection.configName=%s&maxShardsPerNode=%s",
                solr.getSolrUrl(), collectionName, numShards, configurationName, numShards));

        // execute request
        try (CloseableHttpClient client = HttpClients.createMinimal();
             CloseableHttpResponse response = client.execute(createCollection);) {

            if (response.getStatusLine().getStatusCode() > 299) {
                throw new IllegalArgumentException(response.getStatusLine().getReasonPhrase());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Imports the icecat chorus dataset into given collection
     */
    public static void importChorusDataset(QuerqySolrContainer solr, String collectionName) throws IOException {

        // read  data set
       String chorusDataSet = FileUtils.readFileToString(solr.getTestDataPath().toFile(), "UTF-8");

        // compose create collection url
        HttpPost importData = new HttpPost(String.format(
                "%s/%s/update?commit=true",
                solr.getSolrUrl(), collectionName));
        importData.setEntity(new StringEntity(chorusDataSet, ContentType.APPLICATION_JSON));

        // execute request
        try (CloseableHttpClient client = HttpClients.createMinimal();
             CloseableHttpResponse response = client.execute(importData);) {

            if (response.getStatusLine().getStatusCode() > 299) {
                throw new IllegalArgumentException(response.getStatusLine().getReasonPhrase());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
