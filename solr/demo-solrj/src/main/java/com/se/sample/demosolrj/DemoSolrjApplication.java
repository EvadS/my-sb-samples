package com.se.sample.demosolrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSolrjApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSolrjApplication.class, args);
    }

    @Bean
    public SolrClient solrClient() {
        String host = "localhost:8993/solr/my_core";
        Http2SolrClient build = new Http2SolrClient.Builder(host).build();

        return build;
    }
}
