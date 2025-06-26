package com.scrapper.ifood.automations;

import java.util.List;
import java.util.NoSuchElementException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.scrapper.ifood.models.Restaurant;

public class IfoodPageNavigator {

    public WebDriver driver; 

    public IfoodPageNavigator(){
            
    }

    public IfoodPageNavigator CreateDriver(){
        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver();
        return this;
    }

    public IfoodPageNavigator OpenIfoodPage(String foodToScrap) throws InterruptedException{
        driver.get("https://www.ifood.com.br/busca?q="+foodToScrap);
        Thread.sleep(3000);
        return this;
    }

    public IfoodPageNavigator SetLocation(String city) throws InterruptedException{
        driver.findElement(By.className("delivery-input__address")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("address-search-input__button")).click();
        Thread.sleep(1000);

        new Actions(driver).sendKeys(city).perform();

        Thread.sleep(5000);

        driver.findElement(By.className("address-search-list")).findElement(By.xpath("./*[1]")).click();
        Thread.sleep(3000);

        driver.findElement(By.className("address-maps__submit")).click();
        Thread.sleep(1000);

        driver.findElement(By.className("complete-address--save-btn")).click();
        Thread.sleep(5000);
        return this;
    }

    public IfoodPageNavigator loadAllPageData(){
        
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
        catch(Exception e){
            System.out.println(e.getClass().getName()+" "+e.getMessage());
        }

        return this;
    }

    public IfoodPageNavigator ScrapAllRestaurants(List<Restaurant> restaurants){
        
        List<List<Restaurant>> listaDeLista;
        WebElement inRestaurantWrapper = driver.findElement(By.className("merchant-list-v2__wrapper"));
        List<WebElement> inRestaurants = inRestaurantWrapper.findElements(By.className("merchant-list-v2__item-wrapper"));
        
        for (WebElement webElement : inRestaurants) {
            String webElementInnerHTML = webElement.getDomProperty("innerHTML");
            Document document = Jsoup.parse(webElementInnerHTML);
            String restaurantName = document.selectFirst("span.merchant-v2__name").text();
            String linkToRestaurantPage = document.selectFirst("a.merchant-v2__link").attr("href");
            Restaurant restaurant = new Restaurant(restaurantName, linkToRestaurantPage);
            restaurants.add(restaurant);
        }
        
        return this;
    }

    public void Quit(){
        driver.quit();
        System.out.println("hello world!");
    }
}
