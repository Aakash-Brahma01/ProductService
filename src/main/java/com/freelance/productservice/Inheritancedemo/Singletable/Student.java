package com.freelance.productservice.Inheritancedemo.Singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_student")
@DiscriminatorValue("2")
public class Student extends User {
    private double psp;
    private String batch_name;
}
