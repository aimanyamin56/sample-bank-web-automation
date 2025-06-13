// package com.bank.tests;

// import org.openqa.selenium.chrome.ChromeDriver;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;

// import com.bank.base.AbstractWebTest;

// import io.github.bonigarcia.wdm.WebDriverManager;

// public class BasicSeleniumTest extends AbstractWebTest{

//     @BeforeMethod
//     public void setup () {
//         WebDriverManager.chromedriver().setup();
//         driver = new ChromeDriver();
//         driver.manage().window().maximize();
//     }

//     @Test
//     public void testGoogleSearch () {
//         driver.get("https://www.google.com");
//         String title = driver.getTitle();
//         System.out.println("Page title is: " + title);
//     }

//     @AfterMethod
//     @Override
//     public void tearDown () {
//         if (driver != null) {
//             driver.quit();
//         }
//     }

// }