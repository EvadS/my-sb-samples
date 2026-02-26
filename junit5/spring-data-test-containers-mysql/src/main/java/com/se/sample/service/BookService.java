package com.se.sample.service;

import com.se.sample.model.Book;
import com.se.sample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    public Book create(Book book) {
        return bookRepository.save(book);
    }


    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

}
