package com.se.sample.model;

import com.se.sample.CustomService;

public class UserRegisteredEvent {

    private String email;

    public UserRegisteredEvent() {
    }

    public UserRegisteredEvent(String email) {
        this.email = email;
    }

    public UserRegisteredEvent(CustomService customService, String email) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
