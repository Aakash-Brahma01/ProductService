package com.freelance.productservice.services;

import com.freelance.productservice.dtos.CategoryDto;
import com.freelance.productservice.dtos.GetProductTitlesRequestDto;
import com.freelance.productservice.dtos.ProductDto;
import com.freelance.productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
        public Category getCategory(String uuid);
        public List<String> getProductTitles(List<String> categoryUUIDS);
}
