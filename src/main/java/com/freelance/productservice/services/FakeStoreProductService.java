package com.freelance.productservice.services;

import com.freelance.productservice.dtos.FakeStoreProductDto;
import com.freelance.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        GenericProductDto productDto=createGenericProductDtoFromFakeStoreProductDto(product);

        return productDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        ResponseEntity<GenericProductDto> response=restTemplate.postForEntity(createProductRequestUrl,product,GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(getAllProductRequestUrl,FakeStoreProductDto[].class);

        List<GenericProductDto> product=new ArrayList<GenericProductDto>();

        for(FakeStoreProductDto dto: Arrays.stream(response.getBody()).toList()){
            GenericProductDto productDto=createGenericProductDtoFromFakeStoreProductDto(dto);
            product.add(productDto);
        }

        return product;
    }

    private GenericProductDto createGenericProductDtoFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto productDto=new GenericProductDto();
        productDto.setId(fakeStoreProductDto.getId());
        productDto.setTitle(fakeStoreProductDto.getTitle());
        productDto.setDescription(fakeStoreProductDto.getDescription());
        productDto.setPrice(fakeStoreProductDto.getPrice());
        productDto.setImage(fakeStoreProductDto.getImage());
        productDto.setCategory(fakeStoreProductDto.getCategory());

        return productDto;
    }
}
