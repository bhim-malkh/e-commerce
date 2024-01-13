package com.scaler.productservice.repository;

import com.scaler.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Optional<Category> findByIdAndIsActiveTrue(Long id);
    public List<Category> findAllByIsActiveTrue();
}
