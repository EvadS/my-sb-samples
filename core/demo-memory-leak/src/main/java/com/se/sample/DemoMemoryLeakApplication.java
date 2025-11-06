package com.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoMemoryLeakApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMemoryLeakApplication.class, args);
    }

}
