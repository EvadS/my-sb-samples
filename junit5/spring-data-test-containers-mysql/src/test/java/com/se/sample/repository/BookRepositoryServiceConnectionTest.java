package com.se.sample.repository;


import com.se.sample.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class BookRepositoryServiceConnectionTest {

    @Autowired
    private BookRepository bookRepository;


    private static final String MYSQL_IMAGE_TAG = "mysql/mysql-server:8.0.31-1.2.10-server";
    private static final DockerImageName MYSQL_IMAGE = DockerImageName.parse(MYSQL_IMAGE_TAG)
            .asCompatibleSubstituteFor("mysql");

    @Container
    @ServiceConnection
    static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(MYSQL_IMAGE);

    @Test
    public void testEmptyList() {

        List<Book> result = bookRepository.findAll();
        assertEquals(0, result.size());
    }
}
