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

public class CustomersPage extends BasePage {
    @FindBy(xpath="//input[@placeholder='Search Customer']")
    private WebElement searchInput;

    @FindBy(xpath="//table/tbody/tr")
    private List<WebElement> customerRows;
    
    @FindBy(xpath="//button[text()='Delete']")
    private List<WebElement> deleteButtons;

    @FindBy(css=".table tbody tr")
    private List<WebElement> customerTable;

    public CustomersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clearSearch() {
        elementUtils.waitForVisibility(searchInput).clear();
    }

    public void searchByFirstName(String firstName) {
        clearSearch();
        elementUtils.waitForVisibility(searchInput).sendKeys(firstName);
        elementUtils.waitForPageLoaded();
    }

    public void searchByLastName(String lastName) {
        clearSearch();
        elementUtils.waitForVisibility(searchInput).sendKeys(lastName);
        elementUtils.waitForPageLoaded();
    }

    public void searchByPostcode(String postcode) {
        clearSearch();
        elementUtils.waitForVisibility(searchInput).sendKeys(postcode);
        elementUtils.waitForPageLoaded();
    }

    public List<WebElement> getCustomerRows() {
        return customerRows;
    }

    public void deleteCustomer(int rowIndex) {
        List<WebElement> buttons = deleteButtons;
        if (rowIndex >= 0 && rowIndex < buttons.size()) {
            elementUtils.waitForClickable(buttons.get(rowIndex)).click();
            elementUtils.waitForPageLoaded();
        }
    }

    public List<Map<String, String>> getAllCustomers() {
        List<Map<String, String>> customers = new ArrayList<>();
        List<WebElement> rows = customerTable;
    
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            Map<String, String> customer = new HashMap<>();
            customer.put("FirstName", cells.get(0).getText().trim());
            customer.put("LastName", cells.get(1).getText().trim());
            customer.put("Postcode", cells.get(2).getText().trim());
            customers.add(customer);
        }
        return customers;
    }
    
} 