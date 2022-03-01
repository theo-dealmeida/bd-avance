package com.course.bdavance.controller;

import com.course.bdavance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/products")
    public List<Product> list() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }
}
