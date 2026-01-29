package com.authenticationapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessMessageDTO {
    private boolean status;
    private String message;
}
