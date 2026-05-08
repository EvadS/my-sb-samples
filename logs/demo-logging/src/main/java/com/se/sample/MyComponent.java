package com.se.sample;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private static final Logger logger = LogManager.getLogger(MyComponent.class);

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() {
        printActiveProfiles();
    }

    public void printActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            logger.debug("-Active profile : " + profile);
            logger.info(" --Active profile: " + profile);
        }
    }
}
