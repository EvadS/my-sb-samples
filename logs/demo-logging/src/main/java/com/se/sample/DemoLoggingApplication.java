package com.se.sample;

import com.se.sample.controller.Slf4jController;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoLoggingApplication {
    private static final Logger logger = LogManager.getLogger(DemoLoggingApplication.class);

    @PostConstruct
    private void init() {
        logger.debug("PostConstruct. debug");
        logger.info("PostConstruct. init");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoLoggingApplication.class, args);
    }

}
