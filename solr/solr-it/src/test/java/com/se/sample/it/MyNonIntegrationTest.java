package com.se.sample.it;

import org.junit.Test;

public class MyNonIntegrationTest {

    @Test
    public void testCustomProperty() {
        String customProperty = System.getProperty("my.custom.property");
        System.out.println("property: " + customProperty);
        System.out.println("-------------------------------------------------");
        // Assertions or logic based on the property value
    }
}
