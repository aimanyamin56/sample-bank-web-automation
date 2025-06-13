# sample-bank-web-automation
Web Automation Testing for Simple Bank Web System

# Bank Automation Project

## How to Run Tests

1. Make sure you have Java, Maven, and Allure installed.
2. Run the tests:
   ```
   mvn clean test
   ```
3. Generate the Allure report:
   ```
   allure generate target/allure-results -o allure-report
   ```
4. Open the report:
   - Open `allure-report/index.html` in your browser.

## Project Structure

- `src/` - Source code and tests
- `pom.xml` - Maven configuration
- `allure-report/` - Generated Allure report
- `target/allure-results/` - Allure results after test run

## Notes

- This project is for the Assessment technical test.