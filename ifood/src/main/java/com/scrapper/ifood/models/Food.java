package com.scrapper.ifood.models;

import java.math.BigDecimal;

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
    protected BigDecimal discountPrice;
    protected BigDecimal originalPrice;
    protected int portionSize;
  
    public Food(Restaurant restaurant, String name, String description, BigDecimal discountPrice, BigDecimal originalPrice, int portionSize) {
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.portionSize = portionSize;
    }





}
