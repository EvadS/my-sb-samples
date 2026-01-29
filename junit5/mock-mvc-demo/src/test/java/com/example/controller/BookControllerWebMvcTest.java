package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
/** Spring Boot: Testing Controllers with MockMVC */
public class BookControllerWebMvcTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService bookService;

    @Test
    void findAllShouldReturnAllBooks() throws Exception {
        Mockito.when(this.bookService.findAll()).thenReturn(getBooks());

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void findOneShouldReturnValidBook() throws Exception {
        Mockito.when(this.bookService.findOne(1)).thenReturn(getBooks().get(0));

        mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("97 Things Every Java Programmer Should Know"))
                .andExpect(jsonPath("$.author").value("Kevlin Henney, Trisha Gee"))
                .andExpect(jsonPath("$.publisher").value("OReilly Media, Inc."))
                .andExpect(jsonPath("$.releaseDate").value("May 2020"))
                .andExpect(jsonPath("$.isbn").value("9781491952696"))
                .andExpect(jsonPath("$.topic").value("Java"));
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
        return List.of(one, two);
    }

}