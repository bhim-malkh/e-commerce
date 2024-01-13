package com.scaler.productservice.services;

import com.scaler.productservice.entity.Category;
import com.scaler.productservice.exceptions.CategoryNotFoundException;

import java.util.List;

public interface ICategoryService {
    Category getCategory(Long id) throws CategoryNotFoundException;
    List<Category> getAllCategories();
    Category addNewCategory(Category category);
    Category updateExistingCategory(Long id, Category category) throws CategoryNotFoundException;
    Category replaceExistingCategory(Long id, Category category) throws CategoryNotFoundException;
    Category deleteCategory(Long id) throws CategoryNotFoundException;

}
