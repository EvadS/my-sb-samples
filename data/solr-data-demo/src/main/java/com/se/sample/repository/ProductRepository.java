package com.se.sample.repository;



import com.se.sample.Product;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface ProductRepository extends SolrCrudRepository<Product, String> {
    List<Product> findByName(String name);
    List<Product> findByDescriptionContaining(String description);
}
