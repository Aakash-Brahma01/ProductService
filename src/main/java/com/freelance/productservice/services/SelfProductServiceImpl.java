package com.freelance.productservice.services;

import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.models.Category;
import com.freelance.productservice.models.Price;
import com.freelance.productservice.models.Product;
import com.freelance.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public GenericProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(UUID.fromString("05ed5d36-d42e-4483-9edf-989d2939b9fe"));

        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(id);
        genericProductDto.setTitle(product.get().getTitle());
        genericProductDto.setDescription(product.get().getDescription());
        genericProductDto.setImage(product.get().getImage());

        Price price=product.get().getPrice();
        genericProductDto.setPrice(price.getPrice());
        Category category=product.get().getCategory();
        genericProductDto.setCategory(category.getName());

        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());

        Price price=new Price();
        price.setPrice(product.getPrice());

        Category category=new Category();
        category.setName(product.getCategory());

        newProduct.setCategory(category);
        newProduct.setPrice(price);

        Product savedProduct=productRepository.save(newProduct);

        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(101L);
        genericProductDto.setTitle(savedProduct.getTitle());
        genericProductDto.setDescription(savedProduct.getDescription());
        genericProductDto.setImage(savedProduct.getImage());

        Price price1=savedProduct.getPrice();
        genericProductDto.setPrice(price1!=null?price.getPrice():0);
        Category category1=savedProduct.getCategory();
        genericProductDto.setCategory(category1!=null?category.getName():null);

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();

        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        for(Product product:products){
            GenericProductDto genericProductDto=new GenericProductDto();
            genericProductDto.setId(101L);
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setDescription(product.getDescription());
            genericProductDto.setImage(product.getImage());

            Price price=product.getPrice();
            genericProductDto.setPrice(price!=null?price.getPrice():0);
            Category category=product.getCategory();
            genericProductDto.setCategory(category!=null?category.getName():null);

            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(UUID.fromString("05ed5d36-d42e-4483-9edf-989d2939b9fe"));
        productRepository.deleteById(UUID.fromString("05ed5d36-d42e-4483-9edf-989d2939b9fe"));
        Product deletedProduct=product.get();

        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(101L);
        genericProductDto.setTitle(deletedProduct.getTitle());
        genericProductDto.setDescription(deletedProduct.getDescription());
        genericProductDto.setImage(deletedProduct.getImage());

        Price price=deletedProduct.getPrice();
        genericProductDto.setPrice(price.getPrice());
        Category category=deletedProduct.getCategory();
        genericProductDto.setCategory(category.getName());

        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto productDto) {
        //3ac54166-bc00-4423-947c-b1f641648ede

        Product updateProduct = new Product();
        updateProduct.setUuid(UUID.fromString("3ac54166-bc00-4423-947c-b1f641648ede"));
        updateProduct.setTitle(productDto.getTitle());
        updateProduct.setDescription(productDto.getDescription());
        updateProduct.setImage(productDto.getImage());

        Price price=new Price();
        price.setPrice(productDto.getPrice());

        Category category=new Category();
        category.setName(productDto.getCategory());

        updateProduct.setCategory(category);
        updateProduct.setPrice(price);

        Product savedProduct=productRepository.save(updateProduct);

        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(101L);
        genericProductDto.setTitle(savedProduct.getTitle());
        genericProductDto.setDescription(savedProduct.getDescription());
        genericProductDto.setImage(savedProduct.getImage());

        Price price1=savedProduct.getPrice();
        genericProductDto.setPrice(price1!=null?price.getPrice():0);
        Category category1=savedProduct.getCategory();
        genericProductDto.setCategory(category1!=null?category.getName():null);

        return genericProductDto;
    }

}
