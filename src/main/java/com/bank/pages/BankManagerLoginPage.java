package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankManagerLoginPage extends BasePage {
    @FindBy(xpath="//button[contains(text(),'Add Customer')]")
    private WebElement addCustomerBtn;
    
    @FindBy(xpath="//button[contains(text(),'Open Account')]")
    private WebElement openAccountBtn;
    
    @FindBy(xpath="//button[contains(text(),'Customers')]")
    private WebElement customersBtn;

    public BankManagerLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAddCustomer() {
        elementUtils.waitForClickable(addCustomerBtn).click();
        elementUtils.waitForPageLoaded();
        elementUtils.waitForVisibility(By.xpath("//input[@placeholder='First Name']"));
    }

    public void clickOpenAccount() {
        elementUtils.waitForClickable(openAccountBtn).click();
        elementUtils.waitForPageLoaded();
    }

    public void clickCustomers() {
        elementUtils.safeClick(customersBtn);
        elementUtils.waitForPageLoaded();
        elementUtils.waitForVisibility(By.xpath("//input[@placeholder='Search Customer']"));
    }
} 