package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface IProductService {

    Product getSingleProduct(long id) throws ProductNotFoundException;

    List<Product> getAllProducts();
}
