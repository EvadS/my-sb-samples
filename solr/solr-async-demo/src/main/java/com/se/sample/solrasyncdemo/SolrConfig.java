package com.se.sample.solrasyncdemo;

import org.apache.solr.client.solrj.impl.ConcurrentUpdateHttp2SolrClient;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class SolrConfig {

    @Bean
    public ExecutorService solrUpdateExecutor() {
        // Option A: Use a custom Spring Executor and convert it
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("SolrUpdate-");
        executor.initialize();
        return Executors.newSingleThreadExecutor(); // Or wrap the Spring one using java.util.concurrent.Executors
    }

    @Bean
    public ConcurrentUpdateHttp2SolrClient concurrentUpdateClient() {
        Http2SolrClient http2Client = new Http2SolrClient.Builder("http://localhost:8983/solr").build();

        return new ConcurrentUpdateHttp2SolrClient.Builder(
                "http://localhost:8983/solr/my_collection",
                http2Client
        )
                .withQueueSize(100)
                .withThreadCount(5)
                .withExecutorService(solrUpdateExecutor()) // Plug in the executor here
                .build();
    }
}
