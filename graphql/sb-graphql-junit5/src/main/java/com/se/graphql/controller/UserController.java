package com.se.graphql.controller;

import com.se.graphql.models.User;
import com.se.graphql.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserController implements GraphQLQueryResolver {

@Autowired
    UserService userService;
    public List<User> getUsers(){
        List<User> users=userService.getAllUsers();

        return users;
    }
}
