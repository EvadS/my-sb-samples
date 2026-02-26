package com.se.sample.old;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Any repository test class that extends this class
 * will be run inside the local mysql container
 * with the dynamically injected connection string (url, user, passwd)
 * */
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class RepositoryTestBase {
	/**
	 * This method is called to add properties with dynamic values
	 * to the Environment's set of PropertySources.
	 * */
	@DynamicPropertySource
	static void mysqlProperties(DynamicPropertyRegistry registry) {
		MySQLContainer<?> mysqlContainer = MysqlContainersManager.MYSQL_CONTAINER;

		assertTrue(mysqlContainer.isRunning());

		String jdbcUrl = mysqlContainer.getJdbcUrl();
		String username = mysqlContainer.getUsername();
		String password = mysqlContainer.getPassword();

		// dynamically inject url, user, passwd into datasource bean
		registry.add("spring.datasource.url", () -> jdbcUrl);
		registry.add("spring.datasource.username", () -> username);
		registry.add("spring.datasource.password", () -> password);

		log.info("""
        Spring data source is set as follows for the test:
        spring.datasource.url = {}
        spring.datasource.username = {}
        spring.datasource.password = {}
        """,
				jdbcUrl, username, password
		);
	}
}
