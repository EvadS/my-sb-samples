package com.se.sample.service;

import org.springframework.stereotype.Service;

@Service
public class SomeService {

    public String greet(String name) {
        return String.format("Hello, mocked %s!", name);
    }
}
