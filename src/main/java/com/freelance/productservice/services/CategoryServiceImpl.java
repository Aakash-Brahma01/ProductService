package com.freelance.productservice.services;

import com.freelance.productservice.dtos.CategoryDto;
import com.freelance.productservice.dtos.GetProductTitlesRequestDto;
import com.freelance.productservice.dtos.ProductDto;
import com.freelance.productservice.models.Category;
import com.freelance.productservice.models.Product;
import com.freelance.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(String uuid){
        Optional<Category> category=categoryRepository.findById(UUID.fromString(uuid));
        Category category1=category.get();

        //List<Product> products=category1.getProducts();

        return category1;
    }

    @Override
    public List<String> getProductTitles(List<String> categoryUUIDs) {
        List<UUID> uuids = new ArrayList<>();
        for (String uuid: categoryUUIDs) {
            uuids.add(UUID.fromString(uuid));
        }
        List<Category> categories = categoryRepository.findAllById(uuids);
        List<String> titles = new ArrayList<>();

        categories.forEach(category -> {
            category.getProducts().forEach(product -> {
                titles.add(product.getTitle());
            });
        });
        return titles;
    }
}
