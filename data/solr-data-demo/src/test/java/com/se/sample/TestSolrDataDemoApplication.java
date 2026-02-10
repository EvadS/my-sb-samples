package com.se.sample;

import org.springframework.boot.SpringApplication;

public class TestSolrDataDemoApplication {

    public static void main(String[] args) {
        SpringApplication.from(SolrDataDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
