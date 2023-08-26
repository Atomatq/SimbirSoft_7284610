package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By home = By.xpath("//button[@class='btn home']");
    By customerLogin = By.xpath("//button[@ng-click='customer()']");
    By managerLogin = By.xpath("//button[@ng-click='manager()']");


    public void goToCustomerLogin() {
        driver.findElement(customerLogin).click();
    }

}
