package com.se.sample.models;


import lombok.Data;

@Data
public abstract class Operation {
    private Task task = new Task();
}