package org.example.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By customerLogin = By.xpath("//button[@ng-click='customer()']");

    @Step("Перейти на станицу регистрации 'Customer Login'")
    public void goToCustomerLogin() {
        driver.findElement(customerLogin).click();
    }

}
