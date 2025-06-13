package com.bank.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bank.constants.Constants;

public class WebElementUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WebElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME));
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hoverOverElement(WebElement element) {
        if (element != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            System.out.println("Hovered over element: " + element);
        } else {
            System.out.println("Element to hover is null!");
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void safeClick(WebElement element) {
        waitForVisibility(element);
        waitForClickable(element);
        scrollToElement(element);
        element.click();
    }

    public void safeSendKeys(WebElement element, String text) {
        waitForVisibility(element);
        waitForClickable(element);
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        Alert alert = waitForAlert();
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = waitForAlert();
        alert.accept();
    }

    public void dismissAlert() {
        Alert alert = waitForAlert();
        alert.dismiss();
    }

    public void selectDropdownByVisibleText(WebElement dropdown, String visibleText) {
        waitForVisibility(dropdown);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
} 