package org.example.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerPage {
    private WebDriver driver;
    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    By transactionsBtn = By.xpath("//button[@ng-click='transactions()']");
    By depositBtn = By.xpath("//button[@ng-click='deposit()']");
    By depositField = By.xpath("//form[@ng-submit='deposit()']//input");
    By depositSubmit = By.xpath("//form[@ng-submit='deposit()']//button");
    By withdrawlBtn = By.xpath("//button[@ng-click='withdrawl()']");
    By withdrawlField = By.xpath("//form[@ng-submit='withdrawl()']//input");
    By withdrawlSubmit = By.xpath("//form[@ng-submit='withdrawl()']//button");
    By accInfo = By.xpath("//div[@ng-hide='noAccount' and contains(.,'Account Number')]");

    @Step("Перейти на вкладку 'Transactions'")
    public void openTransactions() {
        driver.findElement(transactionsBtn).click();
    }

    @Step("Перейти на вкладку 'Deposit'")
    public CustomerPage openDeposit() {
        driver.findElement(depositBtn).click();
        return this;
    }

    @Step("Перейти на вкладку 'Withdrawl'")
    public CustomerPage openWithdrawl() {
        driver.findElement(withdrawlBtn).click();
        return this;
    }

    @Step("Выполнить пополнение счета (Deposit) на сумму {amount}")
    public CustomerPage deposit(int amount) {
        driver.findElement(depositField).sendKeys(Integer.toString(amount));
        driver.findElement(depositSubmit).click();
        return this;
    }

    @Step("Выполнить списание со счета (Withdrawl) на сумму {amount}'")
    public CustomerPage withdrawl(int amount) {
        driver.findElement(withdrawlField).sendKeys(Integer.toString(amount));
        driver.findElement(withdrawlSubmit).click();
        return this;
    }

    public Map<String, String> getAccInfoCollection() {
        String accInfoLine = driver.findElement(accInfo).getText();
        return Arrays.stream(accInfoLine.split(" , ")).
                        map(s -> s.split(" : ")).
                        collect(Collectors.toMap(s -> s[0], s -> s.length > 1 ? s[1] : ""));
    }


}
