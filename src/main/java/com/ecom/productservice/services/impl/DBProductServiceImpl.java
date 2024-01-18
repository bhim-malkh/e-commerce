package com.ecom.productservice.services.impl;

import com.ecom.productservice.entity.Product;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.repository.ProductRepository;
import com.ecom.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DBProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public DBProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findByIdAndIsActiveTrue(id);
        return product.orElseThrow(() -> getProductNotFoundException(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByIsActiveTrue();
    }

    @Override
    public Product addNewProduct(Product product) {
        product.setCreatedAt(new Date());
        product.setLastUpdatedAt(new Date());
        product.setIsActive(Boolean.TRUE);
        return productRepository.save(product);
    }

    @Override
    public Product updateExistingProduct(Long id, Product p) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(product -> {
            if (p.getTitle() != null)
                product.setTitle(p.getTitle());
            if (p.getDescription() != null)
                product.setDescription(p.getDescription());
            if (p.getCategory() != null)
                product.setCategory(p.getCategory());
            if (p.getPrice() != null)
                product.setPrice(p.getPrice());
            if (p.getImage_url() != null)
                product.setImage_url(p.getImage_url());
            if (p.getIsActive() != null)
                product.setIsActive(p.getIsActive());
            product.setLastUpdatedAt(new Date());
            productRepository.save(product);
        });
        return optionalProduct.orElseThrow(() -> getProductNotFoundException(id));
    }

    @Override
    public Product replaceExistingProduct(Long id, Product p) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(product -> {
            product.setTitle(p.getTitle());
            product.setDescription(p.getDescription());
            product.setCategory(p.getCategory());
            product.setPrice(p.getPrice());
            product.setImage_url(p.getImage_url());
            product.setIsActive(p.getIsActive());
            product.setLastUpdatedAt(new Date());
            productRepository.save(product);
        });
        return optionalProduct.orElseThrow(() -> getProductNotFoundException(id));
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(product -> {
            product.setIsActive(Boolean.FALSE);
            product.setLastUpdatedAt(new Date());
            productRepository.save(product);
        });
        return optionalProduct.orElseThrow(() -> getProductNotFoundException(id));
    }

    private ProductNotFoundException getProductNotFoundException(long id) {
        return new ProductNotFoundException("Product with id:" + id + " not found");
    }

}
