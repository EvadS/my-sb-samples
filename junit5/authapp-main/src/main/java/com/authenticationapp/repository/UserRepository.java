package com.authenticationapp.repository;

import com.authenticationapp.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT * FROM users u where u.email=:username and u.enabled=true")
    Optional<User> findActiveUserByUsername(String username);
}
