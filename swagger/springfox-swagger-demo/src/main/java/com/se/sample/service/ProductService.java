package com.se.sample.service;

import com.se.sample.models.Product;
import java.util.Collection;

public interface ProductService {
    Collection<Product> listAll();

    Product getById(Integer id);

    Product save(Product product);

    void delete(Integer id);
}
