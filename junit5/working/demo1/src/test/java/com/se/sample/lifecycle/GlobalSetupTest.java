package com.se.sample.lifecycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalSetupTest {

    @BeforeAll
    static void init() {
        System.out.println("Подготовка перед ВСЕМИ тестами");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Очистка после ВСЕХ тестов");
    }

    @Test
    void testOne() {
        System.out.println("Первый тест");
    }

    @Test
    void testTwo() {
        System.out.println("Второй тест");
    }

    @Test
    @DisplayName("Проверка сложения: 2 + 2 = 4")
    void additionTest() {
        assertEquals(4, 2 + 2);
    }
}
