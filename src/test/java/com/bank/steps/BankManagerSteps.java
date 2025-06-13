package com.bank.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import static com.bank.steps.Hooks.addCustomerPage;
import static com.bank.steps.Hooks.bankManagerLoginPage;
import static com.bank.steps.Hooks.customersPage;
import static com.bank.steps.Hooks.elementUtils;
import com.bank.utils.AlertUtils;
import com.bank.utils.CustomerOperations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class BankManagerSteps{
    @When("I click Add Customer tab")
    @Step("Click Add Customer tab")
    public void iClickAddCustomerTab() {
        bankManagerLoginPage.clickAddCustomer();
    }

    @And("I input new customer {string} {string} and {string}")
    @Step("Input new customer: {0} {1} {2}")
    public void iInputNewCustomer(String firstName, String lastName, String postcode) {
        addCustomerPage.enterFirstName(firstName);
        addCustomerPage.enterLastName(lastName);
        addCustomerPage.enterPostCode(postcode);
    }

    @And("I click Add Customer button")
    @Step("Click Add Customer button")
    public void iClickAddCustomerButton() {
        addCustomerPage.clickAddCustomer();
    }

    @And("I add multiple customers")
    @Step("Add multiple customers")
    public void iAddMultipleCustomers(DataTable dataTable) {
        List<Map<String, String>> customers = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> customer : customers) {
            String firstName = customer.get("FirstName");
            String lastName = customer.get("LastName");
            String postcode = customer.get("Postcode");

            iInputNewCustomer(firstName, lastName, postcode);
            iClickAddCustomerButton();
            verifyNotificationMessage("Customer added successfully");
        }
    }

    @And("Verify the Notification message {string}")
    @Step("Verify the Notification message: {0}")
    public void verifyNotificationMessage(String message) {
        String actualMessage = addCustomerPage.getNotificationMessage();
        Assert.assertTrue(actualMessage.contains(message), "The notification message does not contain the expected substring: " + message);
        addCustomerPage.acceptAlert();
        AlertUtils.handleAlertWait(elementUtils);
    }

    @And("Go to Customers tab")
    @Step("Go to Customers tab")
    public void goToCustomersTab() {
        bankManagerLoginPage.clickCustomers();
    }

    @And("Verify new customer {string} {string} and {string} at Customer page")
    @Step("Verify new customer at Customer page: {0} {1} {2}")
    public void verifyNewCustomerAtCustomerPage(String firstName, String lastName, String postcode) {
        List<Map<String, String>> customers = customersPage.getAllCustomers();
        Map<String, String> expectedCustomer = new HashMap<>();
        expectedCustomer.put("FirstName", firstName);
        expectedCustomer.put("LastName", lastName);
        expectedCustomer.put("Postcode", postcode);
        CustomerOperations.verifyCustomerExists(expectedCustomer, customers);
    }

    @When("I delete multiple customers")
    @Step("Delete multiple customers")
    public void iDeleteMultipleCustomers(DataTable dataTable) {
        List<Map<String, String>> customers = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> customer : customers) {
            String firstName = customer.get("FirstName");
            String lastName = customer.get("LastName");
            String postcode = customer.get("Postcode");

            boolean deleted = CustomerOperations.deleteCustomer(customersPage, firstName, lastName, postcode);
            Assert.assertTrue(deleted, "Failed to delete customer: " + firstName + " " + lastName);
            
            // Delay to see the deletion
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Then("Verify newly added customers at Customer page")
    @Step("Verify newly added customers at Customer page")
    public void verifyNewlyAddedCustomersatCustomerPage(DataTable dataTable) {
        List<Map<String, String>> customers = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> customer : customers) {
            String firstName = customer.get("FirstName");
            String lastName = customer.get("LastName");
            String postcode = customer.get("Postcode");

            verifyNewCustomerAtCustomerPage(firstName, lastName, postcode);
        }
    }

    @Then("Verify deleted customers at Customer page")
    @Step("Verify deleted customers at Customer page")
    public void verifyDeletedCustomersAtCustomerPage(DataTable dataTable) {
        List<Map<String, String>> customers = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> customer : customers) {
            String firstName = customer.get("FirstName");
            String lastName = customer.get("LastName");
            String postcode = customer.get("Postcode");

            boolean exists = CustomerOperations.customerExists(customersPage, firstName, lastName, postcode);
            Assert.assertFalse(exists, "Customer " + firstName + " " + lastName + " still exists in the customer list");
        }
    }
} 