package com.scaler.productservice.controlleradvices;

import com.scaler.productservice.dtos.ExceptionDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
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

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException(ArithmeticException ex){
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(), ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
