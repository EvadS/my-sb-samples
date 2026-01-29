package com.authenticationapp.repository;

import com.authenticationapp.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
