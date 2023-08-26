package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {
    private WebDriver driver;
    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    By dropDownList = By.xpath("//select[@id='userSelect']");
    By login = By.xpath("//button[@type='submit']");


    public CustomerLoginPage selectName(String name) {
        Select select = new Select(driver.findElement(dropDownList));
        select.selectByVisibleText(name);
        return this;
    }

    public void login() {
        driver.findElement(login).click();
    }

}
