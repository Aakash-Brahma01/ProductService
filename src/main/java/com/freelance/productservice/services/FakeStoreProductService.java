package com.freelance.productservice.services;

import com.freelance.productservice.dtos.FakeStoreProductDto;
import com.freelance.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    private String getProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl="https://fakestoreapi.com/products";
    private String updateProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String getAllProductRequestUrl="https://fakestoreapi.com/products";

    RestTemplateBuilder restTemplateBuilder;
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplate = restTemplateBuilder.build();
    }

    public GenericProductDto getProductById(Long id){

        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(getProductRequestUrl,FakeStoreProductDto.class,id);

        FakeStoreProductDto product=response.getBody();

        GenericProductDto productDto=new GenericProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setCategory(product.getCategory());

        return productDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        ResponseEntity<GenericProductDto> response=restTemplate.postForEntity(createProductRequestUrl,product,GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto[] getAllProducts(){
        ResponseEntity<GenericProductDto[]> response=restTemplate.getForEntity(getAllProductRequestUrl,GenericProductDto[].class);
        return response.getBody();
    }
}
