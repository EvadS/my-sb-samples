package com.se.sample.models;

import lombok.Data;

@Data
public class GenerationRequest {
    public String userId;
    public String reportType;
    public int year;
}
