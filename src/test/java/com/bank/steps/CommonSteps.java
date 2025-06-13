package com.bank.steps;

import org.testng.Assert;

import com.bank.constants.Constants;
import static com.bank.steps.Hooks.driver;
import static com.bank.steps.Hooks.loginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CommonSteps{
    @Given("I am on the bank login page")
    public void iAmOnTheBankLoginPage() {
        driver.get(Constants.BASE_URL);
        Assert.assertTrue(loginPage.isCustomerLoginButtonDisplayed(), "Customer Login button is not displayed");
        Assert.assertTrue(loginPage.isManagerLoginButtonDisplayed(), "Bank Manager Login button is not displayed");
    }

    @And("I click Bank Manager Login button")
    public void iClickBankManagerLoginButton() {
        loginPage.clickManagerLogin();
    }

    @And("I click Customer Login button")
    public void iClickCustomerLoginButton() {
        loginPage.clickCustomerLogin();
    }
} 