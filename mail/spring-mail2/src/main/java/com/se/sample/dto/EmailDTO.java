package com.se.sample.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    @NotNull
    @NotEmpty
    @Email
    private String to;
    @NotNull
    @NotEmpty
    private String subject;
    @NotNull
    @NotEmpty
    private String body;
}
