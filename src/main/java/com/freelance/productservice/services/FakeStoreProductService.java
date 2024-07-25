package com.freelance.productservice.services;

import com.freelance.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.freelance.productservice.dtos.GenericProductDto;
import com.freelance.productservice.exceptions.NotFoundException;
import com.freelance.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Primary
@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto product=fakeStoreProductServiceClient.getProductById(id);
        if(product==null){
            throw new NotFoundException("Product with id "+id+" not found in fakeStore");
        }
        GenericProductDto productDto=createGenericProductDtoFromFakeStoreProductDto(product);

        return productDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        FakeStoreProductDto productDto= fakeStoreProductServiceClient.createProduct(product);
        return createGenericProductDtoFromFakeStoreProductDto(productDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts(){
        List<GenericProductDto> product=new ArrayList<GenericProductDto>();
        for(FakeStoreProductDto dto: fakeStoreProductServiceClient.getAllProducts()){
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
        return createGenericProductDtoFromFakeStoreProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto productDto){
        FakeStoreProductDto fakeStoreProductDto= fakeStoreProductServiceClient.updateProductById(id,productDto);
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
