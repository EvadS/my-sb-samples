package com.se.sample;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

    static final Logger log = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        log.info("-------------------------------------------------------------");
        log.info("logger works correctly");
    }
}
