package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerAccountPage extends BasePage {

    @FindBy(id="accountSelect")
    private WebElement accountDropdown;

    @FindBy(xpath="//div[contains(@class,'center')]/text()[contains(.,'Balance')]/following-sibling::strong[1]")
    private WebElement balanceText;

    @FindBy(xpath="//button[contains(text(),'Deposit')]")
    private WebElement depositButton;

    @FindBy(xpath="//button[contains(text(),'Withdrawl')]")
    private WebElement withdrawButton;

    @FindBy(xpath="//button[contains(text(),'Transactions')]")
    private WebElement transactionsButton;

    public CustomerAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectAccount(String accountNumber) {
        accountDropdown.click();
        Select select = new Select(accountDropdown);
        select.selectByVisibleText(accountNumber);
    }

    public int getBalance() {
        return Integer.parseInt(balanceText.getText().trim());
    }

    public void goToDeposit() {
        elementUtils.safeClick(depositButton);
    }

    public void goToWithdraw() {
        elementUtils.safeClick(withdrawButton);
    }

    public void goToTransactions() {
        elementUtils.safeClick(transactionsButton);
        elementUtils.waitForVisibility(By.cssSelector("table tbody tr"));
    }
}