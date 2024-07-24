package com.freelance.productservice.controllers;

import com.freelance.productservice.dtos.ExceptionDto;
import com.freelance.productservice.dtos.FakeStoreProductDto;
import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.exceptions.NotFoundException;
import com.freelance.productservice.models.Product;
import com.freelance.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) {
        return productService.updateProductById(id,genericProductDto);
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String NotFoundException(Exception e){
//        return e.getMessage();
//    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity(
//                new ExceptionDto(notFoundException.getMessage(),
//                        HttpStatus.NOT_FOUND),
//                HttpStatus.NOT_FOUND
//        );
//    }


}
