package com.se.sample.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


public class UpdateBookmarkPayload {

    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Url is required")
    private  String url;

    public UpdateBookmarkPayload() {
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
