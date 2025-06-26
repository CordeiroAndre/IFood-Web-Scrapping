package com.scrapper.ifood.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    protected Long id;

    protected String link;

    @Column(unique = true)
    protected String name;

     public Restaurant(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public boolean equals(Object obj) {
        Restaurant otherRestaurant = (Restaurant) obj;
        return name.equals(otherRestaurant.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String toString(){
        return name;
    }

    public String getLink(){
        return link;
    }
}
