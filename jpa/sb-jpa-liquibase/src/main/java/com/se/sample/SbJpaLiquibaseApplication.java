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
        // Ensure Liquibase has a JDBC driver when running outside of the full
        // Spring Boot environment (for example during some maven/liquibase runs)
        // If the driver is already provided via properties, don't overwrite it.
        if (System.getProperty("liquibase.driver") == null) {
            // Default to PostgreSQL driver because this project targets Postgres.
            System.setProperty("liquibase.driver", "org.postgresql.Driver");
        }
        // If a JDBC URL isn't supplied to Liquibase, some plugin executions may
        // attempt to use the hibernate URL which Liquibase can't parse. Avoid
        // that by not setting a URL here; prefer configuration in
        // application.properties or liquibase.properties. The driver above
        // is usually sufficient to let Liquibase determine connectivity when
        // running inside Spring Boot with a DataSource.

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
