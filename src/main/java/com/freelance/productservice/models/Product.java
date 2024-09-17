package com.freelance.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{

    private String title;

    private String description;

    private String image;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private Price price;
//    @ManyToMany(mappedBy = "products")
//    private List<Order> orders;
}
