package com.se.sample.it;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.SolrPing;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.SolrContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.solr.SolrContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class SolrIntegrationTest {

    private static SolrClient solrClient;

    // Define the container, using an official Docker image name
    @Container
    public static SolrContainer solrContainer = new SolrContainer(
            DockerImageName.parse("solr:8.11.1")
    );

    @BeforeAll
    static void setUp() {
        // Start the container. This happens automatically with @Container and @Testcontainers,
        // but explicit start can be used in other contexts.
        // solrContainer.start();

        // Build a SolrClient using the container's dynamically mapped host and port
        String solrUrl = "http://" + solrContainer.getHost() + ":" + solrContainer.getSolrPort() + "/solr";
        /// solrClient = new Http2SolrClient.Builder(solrUrl).build();
        solrClient = new HttpSolrClient(solrUrl);
    }

    @Test
    void testSolrConnection() throws Exception {
        // Example of using the Solr client
        /// var response = solrClient.ping("dummy"); // Use a valid core/collection name
       ////// var response = solrClient.ping(); // Use a valid core/collection name

        SolrPing ping = new SolrPing();
        ping.getParams().add("distrib", "true"); //To make it a distributed request against a collection
        SolrPingResponse rsp = ping.process(solrClient, "dummy");
        int status = rsp.getStatus();
        
        Assertions.assertNotNull(status);
    }

    @AfterAll
    static void tearDown() throws Exception {
        if (solrClient != null) {
            solrClient.close();
        }
        // Container stops automatically after all tests in the class run
    }
}
