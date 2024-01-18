package com.ecom.productservice.repository;

import com.ecom.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByIdAndIsActiveTrue(Long id);
    List<Category> findAllByIsActiveTrue();
}
