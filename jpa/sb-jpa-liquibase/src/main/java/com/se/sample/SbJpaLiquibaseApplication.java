package com.se.sample;

import com.se.sample.service.CivilianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbJpaLiquibaseApplication implements CommandLineRunner {
    private static final Logger logger
            = LoggerFactory.getLogger(SbJpaLiquibaseApplication.class);

    private final CivilianService civilianService;

    public SbJpaLiquibaseApplication(CivilianService civilianService) {
        this.civilianService = civilianService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SbJpaLiquibaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("------------------------------------------------");
        logger.info("Civilians Registered in NeuroWatch:");
        civilianService.findAll().forEach(civilian -> {
            logger.info("- National ID: {} | Under surveillance: {}",
                    civilian.getNationalId(), civilian.isUnderSurveillance());
        });
        logger.debug("------------------------------------------------");
    }
}
