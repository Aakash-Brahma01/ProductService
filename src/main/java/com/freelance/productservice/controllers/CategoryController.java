package com.freelance.productservice.controllers;

import com.freelance.productservice.dtos.CategoryDto;
import com.freelance.productservice.dtos.GetProductTitlesRequestDto;
import com.freelance.productservice.dtos.ProductDto;
import com.freelance.productservice.models.Product;
import com.freelance.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("{uuid}")
    public List<ProductDto> getCategory(@PathVariable("uuid")String uuid){
        List<Product> products=categoryService.getCategory(uuid).getProducts();

        List<ProductDto> productDtos=new ArrayList<>();

        for(Product product:products){
            ProductDto productDto=new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @GetMapping("/titles/")
    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto getProductTitlesRequestDto){
        List<String> uuids=getProductTitlesRequestDto.getUuid();
        return categoryService.getProductTitles(uuids);
    }
}
