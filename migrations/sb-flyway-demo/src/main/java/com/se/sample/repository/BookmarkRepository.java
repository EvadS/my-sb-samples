package com.se.sample.repository;


import com.se.sample.domain.Bookmark;
import com.se.sample.domain.BookmarkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<BookmarkInfo> findAllByOrderByCreatedAtDesc();
    Optional<BookmarkInfo> findBookmarkById(Long id);

}
