package com.se.sample.repository;

import com.se.sample.entity.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DAOUser, Integer> {
    DAOUser findByUsername(String name);
}
