package com.scrapper.ifood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrapper.ifood.models.Food;

public interface FoodRespository extends JpaRepository<Food, Long>{

}
