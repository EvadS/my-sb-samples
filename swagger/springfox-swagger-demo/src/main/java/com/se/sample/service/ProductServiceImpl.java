package com.se.sample.service;

import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public Collection<Product> listAll() {
        logger.debug("listAllProducts called");
        return products.values();
    }

    @Override
    public Product getById(Integer id) {
        logger.debug("get product by id: {}",id);

       return Optional.ofNullable(products.get(id))
                .orElseThrow(() -> new ResourceNotFoundException("Element not found"));
    }

    @Override
    public Product save(Product product) {
        logger.debug("saveProduct called");

        Integer id = products.keySet()
                .stream()
                .findFirst()
                .orElseGet(() -> 0);

        id++;
        return products.put(id, product);
    }

    @Override
    public void delete(Integer id) {
        products.remove(id);
    }
}
