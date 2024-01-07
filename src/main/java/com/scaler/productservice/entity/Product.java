package com.scaler.productservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double price;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String image_url;

}
