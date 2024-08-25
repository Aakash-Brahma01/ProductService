package com.freelance.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseModel{

    private String title;

    private String description;

    private String image;
    @ManyToOne
    private Category category;
    private double price;


}
