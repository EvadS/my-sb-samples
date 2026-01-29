package com.example;

import com.example.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MockMvcDemoApplicationTests {

    @Autowired
    private BookController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
