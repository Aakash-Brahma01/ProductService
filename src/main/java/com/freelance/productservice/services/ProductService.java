package com.freelance.productservice.services;

import com.freelance.productservice.dtos.GenericProductDto;

public interface ProductService {
    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto[] getAllProducts();
}
