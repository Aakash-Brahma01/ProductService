package com.freelance.productservice.services;

import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);

    GenericProductDto updateProductById(Long id, GenericProductDto productDto);
}
