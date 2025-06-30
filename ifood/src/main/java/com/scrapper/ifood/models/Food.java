package com.scrapper.ifood.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Food {
    
    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    protected Restaurant restaurant;
    
    protected String name; 
    protected String description;
    protected String discountPrice;
    protected String originalPrice;
    protected String portionSize;
  
    public Food(Restaurant restaurant, String name, String description, String discountPrice, String originalPrice, String portionSize) {
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.portionSize = portionSize;
    }

}
