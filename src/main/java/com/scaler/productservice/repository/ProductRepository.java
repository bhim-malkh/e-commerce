package com.scaler.productservice.repository;

import com.scaler.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIsActiveTrue();

    Optional<Product> findByIdAndIsActiveTrue(Long id);
}
