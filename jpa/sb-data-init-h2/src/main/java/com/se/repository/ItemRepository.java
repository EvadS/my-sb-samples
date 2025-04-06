package com.se.repository;

import java.util.List;
import java.util.Optional;
import com.se.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    Optional<Item> findById(Long name);
    List<Item> findByName(String name);
}