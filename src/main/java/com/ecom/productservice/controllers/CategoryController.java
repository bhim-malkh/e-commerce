package com.ecom.productservice.controllers;

import com.ecom.productservice.entity.Category;
import com.ecom.productservice.exceptions.CategoryNotFoundException;
import com.ecom.productservice.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.addNewCategory(category), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateExistingCategory(@PathVariable("id") Long id, @RequestBody Category category) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.updateExistingCategory(id, category), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> replaceExistingCategory(@PathVariable("id") Long id, @RequestBody Category category) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.replaceExistingCategory(id, category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

}
