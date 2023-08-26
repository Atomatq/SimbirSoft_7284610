import io.qameta.allure.Description;
import org.example.helper.AllureReport;
import org.example.pageObjects.CustomerLoginPage;
import org.example.pageObjects.CustomerPage;
import org.example.pageObjects.MainPage;
import org.example.pageObjects.TransactionsPage;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.example.helper.CollectionsKeys.*;
import static org.example.helper.DateTimeFormats.*;
import static org.example.helper.Helper.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tests extends BaseTest {
    protected CustomerPage customerPage;
    int amount = getFibonacci(getCurrentDay() + 1);
    final String CSV_FILE = amount + ".csv";
    List<String> csvHeaders = Arrays.asList("Дата-времяТранзакции", "Сумма", "ТипТранзакции");
    Map<String, String> accInfoCollection;
    List<Map<String, String>> transactionsTable;

    @Test
    @Description("Тестовое задание №7284610")
    public void test1() {
        new MainPage(webDriver).goToCustomerLogin();
        new CustomerLoginPage(webDriver).selectName(config.getValue("user.login")).login();
        customerPage = new CustomerPage(webDriver);
        accInfoCollection = customerPage.openDeposit().
                deposit(amount).
                openWithdrawl().
                withdrawl(amount).
                getAccInfoCollection();
        assertEquals("0", accInfoCollection.get(accInfo_balance), "Баланс не равен нулю. ");

        customerPage.openTransactions();
        transactionsTable = new TransactionsPage(webDriver).getTableCollection();
        assertEquals(2, transactionsTable.size(),"Некорректное количество транзакций. ");

        //convert date format to 'ДД Месяц ГГГГ ЧЧ:ММ:СС'
        for (Map<String, String> row : transactionsTable) {
            row.put(transaction_dateTime,
                    convertDateTime(row.get(transaction_dateTime), MMM_dd_yyyy_hhMMSS_a, dd_MMM_yyyy_hhMMss));
        }

        createCSV(CSV_FILE, csvHeaders, transactionsTable);
        AllureReport.attachFile(CSV_FILE, "text/csv", ".csv");
    }
}
