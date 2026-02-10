package com.se.sample.models.response;

import lombok.Data;

@Data
public class TaskResponse {
    private String id;
    private String name;

    private String state;
}
