package com.se.sample.demo1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    @Test
    void additionTest() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 должно быть равно 5");
    }

    @Test
    void testAssertions() {
        assertEquals(4, 2 + 2, "Сложение двух чисел должно быть корректным");
        assertTrue(3 > 2, "3 больше 2 - это правда");
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        }, "Ожидалось ArithmeticException при делении на 0");
    }
}
