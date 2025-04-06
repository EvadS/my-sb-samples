package com.se.sample.controller;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}