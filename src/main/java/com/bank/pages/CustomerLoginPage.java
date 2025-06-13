package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage extends BasePage {
    @FindBy(id="userSelect")
    private WebElement userSelect;

    @FindBy(xpath="//button[text()='Login']")
    private WebElement loginBtn;

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectUser(String userName) {
        Select select = new Select(userSelect);
        select.selectByVisibleText(userName);
    }

    public void clickLogin() {
        elementUtils.waitForClickable(loginBtn).click();
        elementUtils.waitForVisibility(By.id("accountSelect"));
    }

    public void customerLoginUser(String userName) {
        selectUser(userName);
        clickLogin();
    }
} 