package com.se.sample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
public class Error {

    @NonNull private Integer code;
    @NonNull private String message;
    @NonNull private String description;
}