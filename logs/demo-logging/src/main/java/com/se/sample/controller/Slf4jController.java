package com.se.sample.controller;


import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Slf4jController {
    private static final Logger logger = LogManager.getLogger(Slf4jController.class);

    @GetMapping("/logs")
    public String greet() {
        logger.info("Slf4jController started");
        logger.debug("Slf4jController details...");
        logger.warn("This is a warning");
        logger.error("An error occurred");

        return "logs in console";
    }
}
