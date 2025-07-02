package com.scrapper.ifood;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.scrapper.ifood.automations.IfoodPageNavigator;
import com.scrapper.ifood.models.Food;
import com.scrapper.ifood.models.Restaurant;
import com.scrapper.ifood.repository.FoodRespository;
import com.scrapper.ifood.repository.RestaurantRepository;

@SpringBootApplication
public class IfoodApplication {

	public static void main(String[] args) throws InterruptedException {
        
        ApplicationContext context = SpringApplication.run(IfoodApplication.class, args);
        List<Restaurant> restaurants = new ArrayList<>();
		IfoodPageNavigator ifoodPageNavigator = new IfoodPageNavigator();
        
        ifoodPageNavigator
        .CreateDriver()
        .OpenIfoodPage("hamburguer")
        .SetLocation("tubarão")
        .loadAllPageData()
        .ScrapAllRestaurants(restaurants);

        RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
        FoodRespository foodRespository = context.getBean(FoodRespository.class);

        restaurants = restaurantRepository.selectAllUnScrappedRestaurants();
        restaurants.forEach(restaurant->{
            try {
                restaurantRepository.save(restaurant);
                List<Food> foods = ifoodPageNavigator.getFoodsFromPage(restaurant);
                for (Food food : foods) {
                foodRespository.save(food);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }   
        });
      
	}

}
