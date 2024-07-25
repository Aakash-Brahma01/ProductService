package com.freelance.productservice.thirdpartyclients.productservice.fakestore;

import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
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

@Service
public class FakeStoreProductServiceClient {

    private String specificProductRequestUrl;
    private String genericProductRequestUrl;

    RestTemplateBuilder restTemplateBuilder;
    RestTemplate restTemplate;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,@Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplate = restTemplateBuilder.build();

        this.genericProductRequestUrl=fakeStoreApiUrl+fakeStoreProductsApiPath;
        this.specificProductRequestUrl=fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";

    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(specificProductRequestUrl,FakeStoreProductDto.class,id);

        FakeStoreProductDto product=response.getBody();

        if(product==null){
            throw new NotFoundException("Product with id "+id+" not found in fakeStore");
        }

        return product;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        ResponseEntity<FakeStoreProductDto> response=restTemplate.postForEntity(genericProductRequestUrl,product,FakeStoreProductDto.class);
        return response.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts(){
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(genericProductRequestUrl,FakeStoreProductDto[].class);
        return List.of(response.getBody());
    }

    public FakeStoreProductDto deleteProductById(Long id){
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto=response.getBody();

        return fakeStoreProductDto;
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto productDto){
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response=restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto=response.getBody();

        return fakeStoreProductDto;
    }


}
