package com.se.sample.lifecycle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class LifecycleTest {

    @BeforeEach
    void setUp() {
        System.out.println("Подготовка перед тестом");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Очистка после теста");
    }

    @Test
    void simpleTest() {
        System.out.println("Выполнение теста");
    }
}
