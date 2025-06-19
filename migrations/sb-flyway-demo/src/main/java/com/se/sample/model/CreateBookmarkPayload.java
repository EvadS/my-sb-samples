package com.se.sample.model;

import jakarta.validation.constraints.NotEmpty;

public class CreateBookmarkPayload {
    @NotEmpty(message = "Title is required")
    String title;
    @NotEmpty(message = "Url is required")
    String url;


    public CreateBookmarkPayload() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

