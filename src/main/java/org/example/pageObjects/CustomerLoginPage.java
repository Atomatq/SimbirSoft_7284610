package org.example.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {
    private WebDriver driver;
    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private By dropDownList = By.xpath("//select[@id='userSelect']");
    private By login = By.xpath("//button[@type='submit']");


    @Step("Выбрать пользователя {name}")
    public CustomerLoginPage selectName(String name) {
        Select select = new Select(driver.findElement(dropDownList));
        select.selectByVisibleText(name);
        return this;
    }

    @Step("Нажать кнопку 'Login'")
    public void login() {
        driver.findElement(login).click();
    }

}
