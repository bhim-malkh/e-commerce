package com.scaler.productservice.services.impl;

import com.scaler.productservice.dtos.FakeStoreProductDTO;
import com.scaler.productservice.entity.Category;
import com.scaler.productservice.entity.Product;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.services.IProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FakeStoreProductService implements IProductService {
    private final Log log = LogFactory.getLog(FakeStoreProductService.class);
    private final AtomicLong counter = new AtomicLong();
    private final RestTemplate restTemplate;

    @Value(value = "${fakestore.endpoint}")
    private String fakeStoreEndPointURI;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        if(id > 20){
            throw new ProductNotFoundException("Product with id: "+id+" Not Found Exception");
        }
        log.info("fakeStoreEndPointURI -> " + fakeStoreEndPointURI);
        Mono<FakeStoreProductDTO> fakeStoreProductDTOMono = WebClient.create()
                .get()
                .uri(fakeStoreEndPointURI + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        return convert(Objects.requireNonNull(fakeStoreProductDTOMono.block()));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Flux<FakeStoreProductDTO> response = WebClient.create()
                .get()
                .uri(fakeStoreEndPointURI)
                .retrieve()
                .bodyToFlux(FakeStoreProductDTO.class);
        System.out.println("Flux ----> "+response);
        for(FakeStoreProductDTO fsProduct: Objects.requireNonNull(response.collectList().block())){
            productList.add(convert(fsProduct));
        }
        return productList;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product updateExistingProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceExistingProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    public List<Product> getAllProductsUsingRestTemplate() {
        List<Product> productList = new ArrayList<>();
        ResponseEntity<List<FakeStoreProductDTO>> response = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {
                });
        for (FakeStoreProductDTO fsProduct : Objects.requireNonNull(response.getBody())) {
            productList.add(convert(fsProduct));
        }
        return productList;
    }

    private Product convert(FakeStoreProductDTO fsProduct) {
        return null;
    }




}
