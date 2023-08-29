package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait waitElement;
    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        waitElement = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    private By tableHeads = By.xpath("//table/thead//a");
    private By tableRows = By.xpath("//table/tbody//td");

    /**
    * Map keys: Date-Time, Amount, Transaction Type
     */
    public List<Map<String, String>> getTableCollection() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().refresh();
        return collectTable();
    }


    private List<Map<String, String>> collectTable() {
        List<Map<String, String>> tableCollection = new ArrayList<>();
        List<String> headsList = new ArrayList<>();
        List<String> cellsList = new ArrayList<>();
        Map<String, String> rowCollection = new HashMap<>();

        //collect heads to List
        driver.findElements(tableHeads).forEach(webElement -> headsList.add(webElement.getText()));

        List<WebElement> rows = driver.findElements(tableRows);
        waitElement.until(elem -> rows.get(0).isDisplayed());
        //collect cells to List
        rows.forEach(webElement -> cellsList.add(webElement.getText()));

        //headsList + cellsList = tableCollection
        for (int i = 0; i < cellsList.size(); i++) {
            int headsCount = headsList.size();
            int column = i % headsCount;

            rowCollection.put(
                    headsList.get(column),
                    cellsList.get(i));

            if (column == headsCount-1) {
                tableCollection.add(new HashMap<>(rowCollection));
                rowCollection.clear();
            }
        }

        return tableCollection;
    }
}
