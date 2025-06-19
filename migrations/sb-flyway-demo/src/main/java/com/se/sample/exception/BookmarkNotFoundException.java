package com.se.sample.exception;

public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(String message) {
        super(message);
    }
}
