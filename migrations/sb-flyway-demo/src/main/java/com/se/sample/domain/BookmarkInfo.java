package com.se.sample.domain;

import java.time.Instant;

/**
 * Projection for {@link Bookmark}
 */
public interface BookmarkInfo {
    Long getId();

    String getTitle();

    String getUrl();
    //   Instant getCreatedAt();

}
