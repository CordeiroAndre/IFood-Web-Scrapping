package com.scrapper.ifood.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Food {
    
    @Id
    @GeneratedValue
    protected Long id;
    protected String name; 
    protected String description;
    protected BigDecimal discountPrice;
    protected BigDecimal originalPrice;
    protected int portionSize;
    
    public Food(String name, String description, BigDecimal discountPrice, BigDecimal originalPrice, int portionSize) {
        this.name = name;
        this.description = description;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.portionSize = portionSize;
    }





}
