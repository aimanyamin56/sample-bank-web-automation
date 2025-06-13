package com.bank.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bank.pages.AddCustomerPage;
import com.bank.pages.BankManagerLoginPage;
import com.bank.pages.CustomerAccountPage;
import com.bank.pages.CustomerLoginPage;
import com.bank.pages.CustomersPage;
import com.bank.pages.DepositPage;
import com.bank.pages.LoginPage;
import com.bank.pages.OpenAccountPage;
import com.bank.pages.TransactionsPage;
import com.bank.pages.WithdrawPage;
import com.bank.utils.AllureUtils;
import com.bank.utils.WebElementUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static BankManagerLoginPage bankManagerLoginPage;
    public static AddCustomerPage addCustomerPage;
    public static CustomersPage customersPage;
    public static OpenAccountPage openAccountPage;
    public static CustomerLoginPage customerLoginPage;
    public static CustomerAccountPage customerAccountPage;
    public static DepositPage depositPage;
    public static WithdrawPage withdrawPage;
    public static TransactionsPage transactionsPage;
    public static WebElementUtils elementUtils;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        bankManagerLoginPage = new BankManagerLoginPage(driver);
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);
        openAccountPage = new OpenAccountPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
        customerAccountPage = new CustomerAccountPage(driver);
        depositPage = new DepositPage(driver);
        withdrawPage = new WithdrawPage(driver);
        transactionsPage = new TransactionsPage(driver);
        elementUtils = new WebElementUtils(driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            AllureUtils.takeScreenshot(driver);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
