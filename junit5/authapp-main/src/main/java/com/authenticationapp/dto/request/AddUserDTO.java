package com.authenticationapp.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AddUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cellNumber;
    private Set<Integer> roleIds;
}
