package com.bank.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bank.constants.Constants;
import com.bank.utils.WebElementUtils;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebElementUtils elementUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME));
        this.elementUtils = new WebElementUtils(driver);
    }

} 