package com.freelance.productservice.Inheritancedemo.Singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_mentor")
@DiscriminatorValue("1")
public class Mentor extends User {
    private double avg_rating;
}
