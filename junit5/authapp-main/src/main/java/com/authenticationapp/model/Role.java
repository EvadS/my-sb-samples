package com.authenticationapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("role")
@Getter
@Setter
public class Role {
    @Id
    private int id;
    private String name;
}
