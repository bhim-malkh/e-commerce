package com.scaler.productservice.services.impl;

import com.scaler.productservice.entity.Category;
import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DBCategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;


    public DBCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findByIdAndIsActiveTrue(id);
        return category.orElseThrow(() -> getCategoryNotFoundException(id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllByIsActiveTrue();
    }

    @Override
    public Category addNewCategory(Category category) {
        category.setIsActive(Boolean.TRUE);
        category.setCreatedAt(new Date());
        category.setLastUpdatedAt(new Date());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateExistingCategory(Long id, Category cat) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        optionalCategory.ifPresent(category -> {
            if (cat.getName() != null)
                category.setName(cat.getName());
            if (cat.getDescription() != null)
                category.setDescription(cat.getDescription());
            if (cat.getIsActive() != null)
                category.setIsActive(cat.getIsActive());
            category.setLastUpdatedAt(new Date());
            categoryRepository.save(category);
        });
        return optionalCategory.orElseThrow(() -> getCategoryNotFoundException(id));
    }

    @Override
    public Category replaceExistingCategory(Long id, Category cat) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        optionalCategory.ifPresent(category -> {
            category.setName(cat.getName());
            category.setDescription(cat.getDescription());
            category.setIsActive(cat.getIsActive());
            category.setLastUpdatedAt(new Date());
            categoryRepository.save(category);
        });
        return optionalCategory.orElseThrow(() -> getCategoryNotFoundException(id));
    }

    @Override
    public Category deleteCategory(Long id) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        optionalCategory.ifPresent(category -> {
            category.setIsActive(Boolean.FALSE);
            category.setLastUpdatedAt(new Date());
            categoryRepository.save(category);
        });
        return optionalCategory.orElseThrow(() -> getCategoryNotFoundException(id));
    }

    private CategoryNotFoundException getCategoryNotFoundException(Long id) {
        return new CategoryNotFoundException("Category with id:" + id + " not found");
    }
}
