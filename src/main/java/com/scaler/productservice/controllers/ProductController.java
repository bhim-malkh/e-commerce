package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.ExceptionDTO;
import com.scaler.productservice.entity.Product;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(@Qualifier("DBProductServiceImpl") IProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addNewProduct(product),HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateExistingProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.updateExistingProduct(id,product),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceExistingProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.replaceExistingProduct(id,product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException ex){
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),"Class level Exception handler is called"),
                        HttpStatus.NOT_FOUND);
    }

}
