package com.scrapper.ifood;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.scrapper.ifood.automations.IfoodPageNavigator;
import com.scrapper.ifood.models.Restaurant;
import com.scrapper.ifood.repository.RestaurantRepository;

@SpringBootApplication
public class IfoodApplication {

	public static void main(String[] args) {
         ApplicationContext context = SpringApplication.run(IfoodApplication.class, args);

		try {
            List<Restaurant> restaurants = new ArrayList<>();

            IfoodPageNavigator ifoodPageNavigator = new IfoodPageNavigator();
            ifoodPageNavigator
            .CreateDriver()
            .OpenIfoodPage("hamburguer")
            .SetLocation("tubarão")
            .loadAllPageData()
            .ScrapAllRestaurants(restaurants)
            .Quit();

            RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);

            restaurants.forEach(restaurant->{
                try {
                    restaurantRepository.save(restaurant);
                } catch (Exception e) {
                    System.out.println("dado duplicado");
                }
            });
           
            /* GET ALL HAMBURGER RESTAURANTS */
            // HashMap<String, List<String>> restaurantsItensRelationship = new HashMap<>();


        // for (int i = 0; i < 2; i++) {
        //         LoadAllPossibleData(driver);
            
        //         WebElement inRestaurantWrapper = driver.findElement(By.className("merchant-list-v2__wrapper"));
        //         List<WebElement> inRestaurants = inRestaurantWrapper
        //                 .findElements(By.className("merchant-list-v2__item-wrapper"));

        //         WebElement webElement = inRestaurants.get(i);
        //         String webElementInnerHTML = webElement.getDomAttribute("innerHTML");
        //         restaurantsItensRelationship.put(webElementInnerHTML,new ArrayList<String>());
        //         new Actions(driver).moveToElement(webElement).perform();
        //         Thread.sleep(1000);
        //         webElement.click();
        //         Thread.sleep(10000);
        //         List<WebElement> restaurantItens = driver.findElements(By.className("dish-card-wrapper"));
        //         for (WebElement restaurantItem : restaurantItens) {
        //             restaurantsItensRelationship.get(webElementInnerHTML).add(restaurantItem.getAttribute("innerHTML"));
        //         }
        //         Thread.sleep(1000);
        //         driver.navigate().back();
        //         Thread.sleep(3000);
        //     }

        //     String filePath = "output.txt";

        //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        //         for (Map.Entry<String, List<String>> entry : restaurantsItensRelationship.entrySet()) {
        //             String restaurantName = entry.getKey().trim();

        //             Document document = Jsoup.parse(restaurantName);
        //             writer.write("Restaurant: " + document.selectFirst("span.merchant-v2__name").text());
        //             writer.newLine();

                    
        //             List<String> items = entry.getValue();
        //             for (String item : items) {
        //                 Document itemDocument = Jsoup.parse(item);
        //                 String dishName = itemDocument.select("h3.dish-card__description").text();
        //                 String dishDescription = itemDocument.select("span.dish-span.dish-card__details").text();
        //                 String priceDiscount = itemDocument.select("span.dish-card__price--discount").text();
        //                 String priceOriginal = itemDocument.select("span.dish-card__price--original").text();
        //                 String price = itemDocument.select("span.dish-card__price").text();
        //                 String HowManyPeopleServes = itemDocument.select("span.dish-info-serves__title").text();
        //                 writer.write("Nome do item:"+dishName);
        //                 writer.newLine();
        //                 writer.write("Descrição do prato:" + dishDescription);
        //                 writer.newLine();
        //                 writer.write("Preço despois do desconto: "+priceDiscount);
        //                 writer.newLine();
        //                 writer.write("Preço antes do desconto: "+priceOriginal);
        //                 writer.newLine();
        //                 writer.write(HowManyPeopleServes);
        //                 writer.newLine();
        //                 writer.write("Preço padrão: " + price);
        //                 writer.newLine();
        //                 writer.write("---------------------------------------------");
        //                 writer.newLine();

        //             }

        //             writer.newLine();
        //         }
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }


        } catch (InterruptedException e) {
            System.out.println("Deu erro no sleep");
        }

	}

}
