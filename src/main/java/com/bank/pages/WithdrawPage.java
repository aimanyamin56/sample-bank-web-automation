package com.bank.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WithdrawPage extends BasePage {

    @FindBy(xpath = "//form[@ng-submit='withdrawl()']/div/input[@ng-model='amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[text()='Withdraw']")
    private WebElement withdrawButton;

    @FindBy(xpath = "//span[contains(@class,'error')]")
    private WebElement withdrawMessage;

    public WithdrawPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void withdrawAmount(int amount) {
        elementUtils.safeSendKeys(amountInput, String.valueOf(amount));
        elementUtils.safeClick(withdrawButton);
    }

    public String getWithdrawMessage() {
        return withdrawMessage.getText();
    }

    public boolean waitForSuccessMessage(String expectedMessage) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.textToBePresentInElement(withdrawMessage, expectedMessage));
    }
}