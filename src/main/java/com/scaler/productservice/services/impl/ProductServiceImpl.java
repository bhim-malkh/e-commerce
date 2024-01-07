package com.scaler.productservice.services.impl;

import com.scaler.productservice.entity.Product;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.services.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
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

}
