package com.se.sample.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpJdkSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SolrConfig {

    private static final Logger logger = LoggerFactory.getLogger(SolrConfig.class);

    @Value("${data.solr.host}")
    private String solrUrl;


    @Bean(name = "ipsuSolrClient")
    public SolrClient IpsSolrClient() {
        return new HttpJdkSolrClient.Builder(solrUrl)
                .build();
    }
}