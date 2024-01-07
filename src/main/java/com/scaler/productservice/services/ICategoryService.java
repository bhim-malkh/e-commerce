package com.scaler.productservice.services;

import com.scaler.productservice.entity.Category;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICategoryService {
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Category addNewCategory(Category category);
    Category updateExistingCategory(Long id, Category category);
    Category replaceExistingCategory(Long id, Category category);
    Category deleteCategory(Long id);

}
