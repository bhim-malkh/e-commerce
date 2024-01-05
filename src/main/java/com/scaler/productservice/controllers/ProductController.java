package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.ExceptionDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException ex){
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),"Class level Exception handler is called"),
                        HttpStatus.NOT_FOUND);
    }

}
