package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerPage {
    private WebDriver driver;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    private Map<String, String> accInfoCollection = new HashMap<>();

    By transactionsBtn = By.xpath("//button[@ng-click='transactions()']");
    By depositBtn = By.xpath("//button[@ng-click='deposit()']");
    By depositField = By.xpath("//form[@ng-submit='deposit()']//input");
    By depositSubmit = By.xpath("//form[@ng-submit='deposit()']//button");
    By withdrawlBtn = By.xpath("//button[@ng-click='withdrawl()']");
    By withdrawlField = By.xpath("//form[@ng-submit='withdrawl()']//input");
    By withdrawlSubmit = By.xpath("//form[@ng-submit='withdrawl()']//button");
    By accInfo = By.xpath("//div[@ng-hide='noAccount' and contains(.,'Account Number')]");

    public void openTransactions() {
        driver.findElement(transactionsBtn).click();
    }

    public CustomerPage openDeposit() {
        driver.findElement(depositBtn).click();
        return this;
    }

    public CustomerPage openWithdrawl() {
        driver.findElement(withdrawlBtn).click();
        return this;
    }

    public CustomerPage deposit(int amount) {
        driver.findElement(depositField).sendKeys(Integer.toString(amount));
        driver.findElement(depositSubmit).click();
        return this;
    }

    public CustomerPage withdrawl(int amount) {
        driver.findElement(withdrawlField).sendKeys(Integer.toString(amount));
        driver.findElement(withdrawlSubmit).click();
        return this;
    }

    public Map<String, String> getAccInfoCollection() {
        String accInfoLine = driver.findElement(accInfo).getText();
        return accInfoCollection =
                Arrays.stream(accInfoLine.split(" , ")).
                        map(s -> s.split(" : ")).
                        collect(Collectors.toMap(s -> s[0], s -> s.length > 1 ? s[1] : ""));
    }


}
