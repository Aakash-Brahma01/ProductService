package com.freelance.productservice.controllers;

import com.freelance.productservice.dtos.FakeStoreProductDto;
import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.models.Product;
import com.freelance.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    //    @Autowired
    // field injection
    //private ProductService productService;

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public GenericProductDto[] getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") Long id) {

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product) {
        
    }
}
