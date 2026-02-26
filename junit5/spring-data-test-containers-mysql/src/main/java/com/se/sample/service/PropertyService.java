package com.se.sample.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Value("${sample.value}")
    private String sampleValue;

    @PostConstruct
    public void init() {
        int a;
        System.out.println("sampleValue; " + sampleValue);
    }

}
