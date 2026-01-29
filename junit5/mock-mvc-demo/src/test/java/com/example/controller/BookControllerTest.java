package com.example.controller;

import com.example.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
/** Spring Boot: Testing Controllers with Functional Test */
public class BookControllerTest {

    @Autowired
    MockMvc mvc;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookController bookController;

    @Test
    void findAllShouldReturnAllBooks() throws Exception {

        ResponseEntity<Book[]> entity = restTemplate.getForEntity("/books", Book[].class);

        assertEquals(HttpStatus.OK,entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,entity.getHeaders().getContentType());

        Book[] books = entity.getBody();
        assertTrue(books.length >= 3);
        assertEquals("97 Things Every Java Programmer Should Know",books[0].getTitle());
        assertEquals("Spring Boot: Up and Running",books[1].getTitle());
        assertEquals("Hacking with Spring Boot 2.3: Reactive Edition",books[2].getTitle());

    }

    /**
     * Second way to test the Endpoint.
     * @throws Exception
     */
    @Test
    void findAllShouldReturnAllBooksAlternative() throws Exception {

        Assertions.assertNotNull(bookController);
        List<Book> books = bookController.findAll();
        Assertions.assertNotNull(books);

        String response = this.restTemplate.getForObject("http://localhost:" + port + "/books",
                String.class);
        System.out.println(response);
        assertTrue(response.contains("Java"));

    }

    @Test
    void shouldReturnAllBooksUsingExchange() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<Book>> entity = restTemplate.exchange("/books", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Book>>() {});

        assertEquals(HttpStatus.OK,entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,entity.getHeaders().getContentType());

        List<Book> books = entity.getBody();
        assertTrue(books.size() >= 3);
    }

    @Test
    void shouldReturnAValidBook() {
        Book book = restTemplate.getForObject("/books/1", Book.class);
        assertEquals(1,book.getId());
        assertEquals("97 Things Every Java Programmer Should Know",book.getTitle());
        assertEquals("Kevlin Henney, Trisha Gee",book.getAuthor());
        assertEquals("OReilly Media, Inc.",book.getPublisher());
        assertEquals("May 2020", book.getReleaseDate());
        assertEquals("9781491952696",book.getIsbn());
        assertEquals("Java",book.getTopic());
    }

    @Test
    void invalidBookIdShouldReturn404() {
        ResponseEntity<Book> entity = restTemplate.getForEntity("/books/99", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

    private List<Book> getBooks() {
        Book one = new Book(1,
                "97 Things Every Java Programmer Should Know",
                "Kevlin Henney, Trisha Gee",
                "OReilly Media, Inc.",
                "May 2020",
                "9781491952696",
                "Java");
        Book two = new Book(2,
                "Spring Boot: Up and Running",
                "Mark Heckler",
                "OReilly Media, Inc.",
                "February 2021",
                "9781492076919",
                "Spring");
        Book three = new Book(3,
                "Hacking with Spring Boot 2.3: Reactive Edition",
                "Greg L. Turnquist",
                "Amazon.com Services LLC",
                "May 2020",
                "B086722L4L",
                "Spring");

        return Arrays.asList(one,two,three);
    }
}
