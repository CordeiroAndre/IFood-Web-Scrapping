package com.scrapping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Main {

    static void LoadAllPossibleData(WebDriver driver) throws InterruptedException {
        boolean canLoadMoreData = true;
        try {
            while (canLoadMoreData) {
                WebElement loadMore = driver.findElement(By.className("cardstack-nextcontent"));
                loadMore.click();
                Thread.sleep(3000);
            }
        } catch (NoSuchElementException e) {
            canLoadMoreData = false;
        }
    }

    public static void main(String[] args) {
        try {

            WebDriver driver = new ChromeDriver();

            /* OPEN IFOOD PAGE WITH THE HAMBURGUER QUERY */
            driver.get("https://www.ifood.com.br/busca?q=hamburguer");
            Thread.sleep(3000);

            /* DEFINE THE LOCATION */
            driver.findElement(By.className("delivery-input__address")).click();
            Thread.sleep(2000);

            driver.findElement(By.className("address-search-input__button")).click();
            Thread.sleep(1000);

            new Actions(driver)
                    .sendKeys("Tubarão")
                    .perform();

            Thread.sleep(5000);

            driver.findElement(By.className("address-search-list")).findElement(By.xpath("./*[1]")).click();
            Thread.sleep(3000);

            driver.findElement(By.className("address-maps__submit")).click();
            Thread.sleep(1000);

            driver.findElement(By.className("complete-address--save-btn")).click();
            Thread.sleep(5000);

          

            /* GET ALL HAMBURGER RESTAURANTS */
            HashMap<String, List<String>> restaurantsItensRelationship = new HashMap<>();


        for (int i = 0; i < 2; i++) {
                LoadAllPossibleData(driver);
            
                WebElement inRestaurantWrapper = driver.findElement(By.className("merchant-list-v2__wrapper"));
                List<WebElement> inRestaurants = inRestaurantWrapper
                        .findElements(By.className("merchant-list-v2__item-wrapper"));

                WebElement webElement = inRestaurants.get(i);
                String webElementInnerHTML = webElement.getAttribute("innerHTML");
                restaurantsItensRelationship.put(webElementInnerHTML,new ArrayList<String>());
                new Actions(driver).moveToElement(webElement).perform();
                Thread.sleep(1000);
                webElement.click();
                Thread.sleep(10000);
                List<WebElement> restaurantItens = driver.findElements(By.className("dish-card-wrapper"));
                for (WebElement restaurantItem : restaurantItens) {
                    restaurantsItensRelationship.get(webElementInnerHTML).add(restaurantItem.getAttribute("innerHTML"));
                }
                Thread.sleep(1000);
                driver.navigate().back();
                Thread.sleep(3000);
            }

            String filePath = "output.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, List<String>> entry : restaurantsItensRelationship.entrySet()) {
                    String restaurantName = entry.getKey().trim();

                    Document document = Jsoup.parse(restaurantName);
                    writer.write("Restaurant: " + document.selectFirst("span.merchant-v2__name").text());
                    writer.newLine();

                    
                    List<String> items = entry.getValue();
                    for (String item : items) {
                        Document itemDocument = Jsoup.parse(item);
                        String dishName = itemDocument.select("h3.dish-card__description").text();
                        String dishDescription = itemDocument.select("span.dish-span.dish-card__details").text();
                        String priceDiscount = itemDocument.select("span.dish-card__price--discount").text();
                        String priceOriginal = itemDocument.select("span.dish-card__price--original").text();
                        String price = itemDocument.select("span.dish-card__price").text();
                        String HowManyPeopleServes = itemDocument.select("span.dish-info-serves__title").text();
                        writer.write("Nome do item:"+dishName);
                        writer.newLine();
                        writer.write("Descrição do prato:" + dishDescription);
                        writer.newLine();
                        writer.write("Preço despois do desconto: "+priceDiscount);
                        writer.newLine();
                        writer.write("Preço antes do desconto: "+priceOriginal);
                        writer.newLine();
                        writer.write(HowManyPeopleServes);
                        writer.newLine();
                        writer.write("Preço padrão: " + price);
                        writer.newLine();
                        writer.write("---------------------------------------------");
                        writer.newLine();

                    }

                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            driver.quit();

        } catch (InterruptedException e) {
            System.out.println("Deu erro no sleep");
        }
    }

}