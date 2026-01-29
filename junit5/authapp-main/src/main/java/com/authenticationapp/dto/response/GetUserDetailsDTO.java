package com.authenticationapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserDetailsDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String username;

}
