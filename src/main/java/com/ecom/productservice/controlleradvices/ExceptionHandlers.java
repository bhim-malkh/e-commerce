package com.ecom.productservice.controlleradvices;

import com.ecom.productservice.dtos.ExceptionDTO;
import com.ecom.productservice.exceptions.CategoryNotFoundException;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException ex){
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), "Check the product id, it might not exist...");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCategoryNotFoundException(CategoryNotFoundException ex){
        return new ResponseEntity<>(
                new ExceptionDTO(ex.getMessage(), "Check the category id"),
                HttpStatus.NOT_FOUND);
    }
}
