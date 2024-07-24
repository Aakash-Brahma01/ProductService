package com.freelance.productservice.services;

import com.freelance.productservice.dtos.FakeStoreProductDto;
import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    private String specificProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String genericProductRequestUrl="https://fakestoreapi.com/products";

    RestTemplateBuilder restTemplateBuilder;
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplate = restTemplateBuilder.build();
    }

    public GenericProductDto getProductById(Long id) throws NotFoundException {

        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(specificProductRequestUrl,FakeStoreProductDto.class,id);

        FakeStoreProductDto product=response.getBody();

        if(product==null){
            throw new NotFoundException("Product with id "+id+" not found in fakeStore");
        }

        GenericProductDto productDto=createGenericProductDtoFromFakeStoreProductDto(product);

        return productDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        ResponseEntity<GenericProductDto> response=restTemplate.postForEntity(genericProductRequestUrl,product,GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(genericProductRequestUrl,FakeStoreProductDto[].class);

        List<GenericProductDto> product=new ArrayList<GenericProductDto>();

        for(FakeStoreProductDto dto: Arrays.stream(response.getBody()).toList()){
            GenericProductDto productDto=createGenericProductDtoFromFakeStoreProductDto(dto);
            product.add(productDto);
        }

        return product;
    }

    //working code but with no response back

//    @Override
//    public void updateProductById(Long id, GenericProductDto product){
//        restTemplate.put(updateProductRequestUrl,product,id);
//    }
//
//    @Override
//    public void deleteProductById(Long id){
//        restTemplate.delete(deleteProductRequestUrl,id);
//    }

    @Override
    public GenericProductDto deleteProductById(Long id){
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto=response.getBody();

        GenericProductDto productDto=createGenericProductDtoFromFakeStoreProductDto(fakeStoreProductDto);

        return productDto;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto productDto){
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response=restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto=response.getBody();
        GenericProductDto genericProductDto=createGenericProductDtoFromFakeStoreProductDto(fakeStoreProductDto);
        return genericProductDto;
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
