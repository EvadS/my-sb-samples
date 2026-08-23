package com.se.sample.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Base <T>{
    private T data;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SubError> errors;

    public Base(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
