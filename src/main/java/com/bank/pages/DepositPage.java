package com.bank.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DepositPage extends BasePage {

    @FindBy(xpath = "//form[@ng-submit='deposit()']/div/input[@ng-model='amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[text()='Deposit']")
    private WebElement depositButton;

    @FindBy(xpath = "//span[contains(@class,'error')]")
    private WebElement depositMessage;

    public DepositPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void depositAmount(int amount) {
        elementUtils.safeSendKeys(amountInput, String.valueOf(amount));
        elementUtils.safeClick(depositButton);
    }

    public String getDepositMessage() {
        return depositMessage.getText();
    }

    public boolean waitForSuccessMessage(String expectedMessage) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.textToBePresentInElement(depositMessage, expectedMessage));
    }
}