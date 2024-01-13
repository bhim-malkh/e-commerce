package com.scaler.productservice.services;

import com.scaler.productservice.entity.Product;
import com.scaler.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface IProductService {

    Product getProduct(long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product updateExistingProduct(Long id, Product product) throws ProductNotFoundException;

    Product replaceExistingProduct(Long id,Product product) throws ProductNotFoundException;

    Product deleteProduct(Long id) throws ProductNotFoundException;

}
