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
    public Product getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateExistingProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateExistingProduct(id,product);
    }

    @PutMapping("/{id}")
    public Product replaceExistingProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceExistingProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProduct(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException ex){
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),"Class level Exception handler is called"),
                        HttpStatus.NOT_FOUND);
    }

}
