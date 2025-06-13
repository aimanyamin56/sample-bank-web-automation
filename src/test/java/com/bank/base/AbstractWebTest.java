package com.bank.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.bank.pages.AddCustomerPage;
import com.bank.pages.BankManagerLoginPage;
import com.bank.pages.CustomerLoginPage;
import com.bank.pages.CustomersPage;
import com.bank.pages.LoginPage;
import com.bank.pages.OpenAccountPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractWebTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected BankManagerLoginPage bankManagerLoginPage;
    protected AddCustomerPage addCustomerPage;
    protected CustomersPage customersPage;
    protected OpenAccountPage openAccountPage;
    protected CustomerLoginPage customerLoginPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize page objects
        loginPage = new LoginPage(driver);
        bankManagerLoginPage = new BankManagerLoginPage(driver);
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);
        openAccountPage = new OpenAccountPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
