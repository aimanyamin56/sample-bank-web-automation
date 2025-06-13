package com.bank.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionsPage extends BasePage {

    @FindBy(xpath = "//button[text()='Back']")
    private WebElement backButton;

    @FindBy(css = "table tbody tr")
    private List<WebElement> transactionRows;

    public TransactionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<Map<String, String>> getTransactions() {
        List<Map<String, String>> transactions = new ArrayList<>();
        for (WebElement row : transactionRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3) {
                Map<String, String> tx = new HashMap<>();
                tx.put("Date-Time", cells.get(0).getText().trim());
                tx.put("Amount", cells.get(1).getText().trim());
                tx.put("Transaction Type", cells.get(2).getText().trim());
                transactions.add(tx);
            }
        }
        return transactions;
    }

    public void goBackToAccountDashboard() {
        elementUtils.safeClick(backButton);
    }
}