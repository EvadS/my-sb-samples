package com.se.sample.controller;


import com.se.sample.domain.Bookmark;
import com.se.sample.model.CreateBookmarkPayload;
import com.se.sample.domain.BookmarkInfo;
import com.se.sample.exception.BookmarkNotFoundException;
import com.se.sample.model.UpdateBookmarkPayload;
import com.se.sample.repository.BookmarkRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkRepository bookmarkRepository;
    BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping
    List<BookmarkInfo> getBookmarks() {
        return bookmarkRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookmarkInfo> getBookmarkById(@PathVariable Long id) {
        var bookmark =
                bookmarkRepository.findBookmarkById(id)
                        .orElseThrow(()-> new BookmarkNotFoundException("Bookmark not found"));
        return ResponseEntity.ok(bookmark);
    }

    @PostMapping
    ResponseEntity<Void> createBookmark(
            @Valid @RequestBody CreateBookmarkPayload payload) {
        var bookmark = new Bookmark();
        bookmark.setTitle(payload.getTitle());
        bookmark.setUrl(payload.getUrl());
      //  bookmark.setCreatedAt(Instant.now());
        var savedBookmark = bookmarkRepository.save(bookmark);
        var url = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(savedBookmark.getId());
        return ResponseEntity.created(url).build();
    }


    @ExceptionHandler(BookmarkNotFoundException.class)
    ResponseEntity<Void> handle(BookmarkNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateBookmark(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookmarkPayload payload) {
        var bookmark =
                bookmarkRepository.findById(id)
                        .orElseThrow(()-> new BookmarkNotFoundException("Bookmark not found"));
        bookmark.setTitle(payload.getTitle());
        bookmark.setUrl(payload.getUrl());
       ///   bookmark.setUpdatedAt(Instant.now());
        bookmarkRepository.save(bookmark);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    void deleteBookmark(@PathVariable Long id) {
        var bookmark =
                bookmarkRepository.findById(id)
                        .orElseThrow(()-> new BookmarkNotFoundException("Bookmark not found"));
        bookmarkRepository.delete(bookmark);
    }
}
