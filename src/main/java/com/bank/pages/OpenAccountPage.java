package com.bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {
    @FindBy(id="userSelect")
    private WebElement customerDropdown;

    @FindBy(id="currency")
    private WebElement currencyDropdown;

    @FindBy(xpath="//button[text()='Process']")
    private WebElement processBtn;

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void selectCustomer(String customerName) {
        Select select = new Select(customerDropdown);
        select.selectByVisibleText(customerName);
    }

    public void selectCurrency(String currency) {
        Select select = new Select(currencyDropdown);
        select.selectByVisibleText(currency);
    }

    public void clickProcess() {
        elementUtils.waitForClickable(processBtn).click();
    }
} 