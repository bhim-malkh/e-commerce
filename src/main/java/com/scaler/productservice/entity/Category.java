package com.scaler.productservice.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Category extends BaseEntity{
    private String name;
    private String description;
}
