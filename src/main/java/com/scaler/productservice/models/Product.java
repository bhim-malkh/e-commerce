package com.scaler.productservice.models;

public record Product (long id, String title,double price, Category category, String description, String imageUrl){
}
