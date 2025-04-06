package com.se.sample.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description =
        "Class representing a person tracked by the application.")
public class Person {
    @ApiModelProperty(notes = "Unique id. Two persons can't have the same id.",
            example = "1", required = true, position = 0)
    private int id;
    @ApiModelProperty(notes = "First name of the person.",
            example = "John", required = true, position = 1)
    private String firstName;
    @ApiModelProperty(notes = "Last name of the person.",
            example = "Doe", required = true, position = 2)
    private String lastName;
    @ApiModelProperty(notes = "Age of the person. Non-negative integer",
            example = "42", position = 3)
    private int age;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // … Constructor, getters, setters, ...
}