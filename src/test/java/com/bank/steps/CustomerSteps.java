package com.bank.steps;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import static com.bank.steps.Hooks.customerAccountPage;
import static com.bank.steps.Hooks.customerLoginPage;
import static com.bank.steps.Hooks.depositPage;
import static com.bank.steps.Hooks.transactionsPage;
import static com.bank.steps.Hooks.withdrawPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class CustomerSteps{
    private int expectedBalance = 0;

    @When("I select {string} from dropdown")
    @Step("Select customer: {0} from dropdown")
    public void iSelectCustomer(String customerName) {
        customerLoginPage.selectUser(customerName);
    }

    @And("I click Login button")
    @Step("Click Login button")
    public void iClickLoginButton() {
        customerLoginPage.clickLogin();
    }

    @And("I select {string} from account dropdown")
    @Step("Select account: {0} from account dropdown")
    public void iSelectAccount(String accountNumber) {
        customerAccountPage.selectAccount(accountNumber);
        expectedBalance = customerAccountPage.getBalance();
    }

    @And("I perform following transactions")
    @Step("Perform following transactions")
    public void iPerformTransactions(DataTable dataTable) {
        List<Map<String, String>> transactions = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> tx : transactions) {
            String type = tx.get("type");
            int amount = Integer.parseInt(tx.get("amount"));
            if (type.equalsIgnoreCase("Credit")) {
                customerAccountPage.goToDeposit();
                depositPage.depositAmount(amount);
                Assert.assertTrue(depositPage.waitForSuccessMessage("Deposit Successful"), "Withdraw Fail");
                expectedBalance += amount;
            } else if (type.equalsIgnoreCase("Debit")) {
                customerAccountPage.goToWithdraw();
                withdrawPage.withdrawAmount(amount);
                Assert.assertTrue(withdrawPage.waitForSuccessMessage("Transaction successful"), "Withdraw Fail");
                expectedBalance -= amount;
            }
            //Handle test flakiness
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @And("I Go to Transaction tab")
    @Step("Go to Transaction tab")
    public void iGoToTransactionTab() {
        customerAccountPage.goToTransactions();
    }

    @Then("Verify transaction page")
    @Step("Verify transaction page")
    public void verifyTransactionPage(DataTable dataTable) {
        List<Map<String, String>> expectedTransactions = dataTable.asMaps(String.class, String.class);
        List<Map<String, String>> actualTransactions = transactionsPage.getTransactions();

        Assert.assertTrue(!actualTransactions.isEmpty(), "No transactions found!");
        Assert.assertEquals(actualTransactions.size(), expectedTransactions.size(), "Transaction count mismatch!");

        for (int i = 0; i < expectedTransactions.size(); i++) {
            Map<String, String> expected = expectedTransactions.get(i);
            Map<String, String> actual = actualTransactions.get(i);
    
            Assert.assertEquals(actual.get("Amount").replaceAll("[^0-9]", ""), 
                expected.get("amount"), "Amount mismatch at row " + (i+1));
            Assert.assertEquals(actual.get("Transaction Type").trim().toLowerCase(), 
                expected.get("type").trim().toLowerCase(), "Type mismatch at row " + (i+1));
        }
    }

    @And("Go back to Account Dashboard")
    @Step("Go back to Account Dashboard")
    public void goBackToAccountDashboard() {
        transactionsPage.goBackToAccountDashboard();
    }

    @And("Verify account balance")
    @Step("Verify account balance")
    public void verifyAccountBalance() {
        int actualBalance = customerAccountPage.getBalance();
        Assert.assertEquals(actualBalance, expectedBalance, "Balance mismatch!");
    }
}