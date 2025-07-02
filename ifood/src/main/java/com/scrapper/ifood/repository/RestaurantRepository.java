package com.scrapper.ifood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scrapper.ifood.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

@Query(value = """
    SELECT r.*
    FROM restaurant r
    LEFT JOIN food f ON r.id = f.restaurant_id
    GROUP BY r.id
    HAVING COUNT(f.name) = 0
    """, nativeQuery = true)
    List<Restaurant> selectAllUnScrappedRestaurants();
}
