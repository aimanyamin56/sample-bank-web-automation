package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath="//button[text()='Customer Login']")
    private WebElement customerLoginBtn;
    
    @FindBy(xpath="//button[text()='Bank Manager Login']")
    private WebElement managerLoginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCustomerLoginButtonDisplayed() {
        return elementUtils.waitForVisibility(customerLoginBtn).isDisplayed();
    }

    public boolean isManagerLoginButtonDisplayed() {
        return elementUtils.waitForVisibility(managerLoginBtn).isDisplayed();
    }

    public void clickCustomerLogin() {
        elementUtils.waitForClickable(customerLoginBtn).click();
        elementUtils.waitForVisibility(By.id("userSelect"));
    }

    public void clickManagerLogin() {
        elementUtils.waitForClickable(managerLoginBtn).click();
    }
} 