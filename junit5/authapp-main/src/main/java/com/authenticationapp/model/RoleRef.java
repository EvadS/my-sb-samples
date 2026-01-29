package com.authenticationapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_role")
@Getter
@Setter
public class RoleRef {
    private int role;
}
