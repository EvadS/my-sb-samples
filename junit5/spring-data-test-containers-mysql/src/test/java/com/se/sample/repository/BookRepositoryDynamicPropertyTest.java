package com.se.sample.repository;

import com.se.sample.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ContextConfiguration and ApplicationContextInitializer
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Sql("/db/jpa/history/schema.sql")
@Testcontainers
public class BookRepositoryDynamicPropertyTest {

    @Autowired
    private BookRepository bookRepository;

    private static final String MYSQL_IMAGE_TAG = "mysql/mysql-server:8.0.31-1.2.10-server";
    private static final DockerImageName MYSQL_IMAGE = DockerImageName.parse(MYSQL_IMAGE_TAG)
            .asCompatibleSubstituteFor("mysql");

    @Container
     static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(MYSQL_IMAGE);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Test
    public void testEmptyList() {
        List<Book> result = bookRepository.findAll();
        assertEquals(0, result.size());
    }
}
