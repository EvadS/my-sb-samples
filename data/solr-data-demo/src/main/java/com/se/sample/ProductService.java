package com.se.sample;

import com.se.sample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> searchProductsByDescription(String description) {
        return productRepository.findByDescriptionContaining(description);
    }
}