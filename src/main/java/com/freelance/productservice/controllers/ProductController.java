package com.freelance.productservice.controllers;

import com.freelance.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public void getAllProducts() {}

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product with id " + id + " found";
    }

    @DeleteMapping("{id}")
    public void deleteProductById(Long id) {}

    @PostMapping
    public String createProduct() {
        return "product created "+ UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById() {}
}
