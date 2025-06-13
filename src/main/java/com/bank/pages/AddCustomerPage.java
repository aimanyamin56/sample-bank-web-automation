package com.bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage extends BasePage {
    @FindBy(xpath="//input[@placeholder='First Name']")
    private WebElement firstNameInput;

    @FindBy(xpath="//input[@placeholder='Last Name']")
    private WebElement lastNameInput;

    @FindBy(xpath="//input[@placeholder='Post Code']")
    private WebElement postcodeInput;
    
    @FindBy(xpath="//button[text()='Add Customer']")
    private WebElement addCustomerBtn;

    public AddCustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        elementUtils.safeSendKeys(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        elementUtils.safeSendKeys(lastNameInput, lastName);
    }

    public void enterPostCode(String postcode) {
        elementUtils.safeSendKeys(postcodeInput, postcode);
    }

    public void clickAddCustomer() {
        elementUtils.safeClick(addCustomerBtn);
    }

    public String getNotificationMessage() {
        return elementUtils.getAlertText();
    }

    public void acceptAlert() {
        elementUtils.acceptAlert();
    }
} 