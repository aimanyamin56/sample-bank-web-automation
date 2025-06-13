package com.bank.utils;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.bank.pages.CustomersPage;

public class CustomerOperations {
    /**
     * Verifies if a customer exists in the list
     * @param expectedCustomer The customer to verify
     * @param actualCustomers List of actual customers
     */
    public static void verifyCustomerExists(Map<String, String> expectedCustomer, List<Map<String, String>> actualCustomers) {
        boolean found = actualCustomers.stream().anyMatch(actual ->
            actual.get("FirstName").equals(expectedCustomer.get("FirstName")) &&
            actual.get("LastName").equals(expectedCustomer.get("LastName")) &&
            actual.get("Postcode").equals(expectedCustomer.get("Postcode"))
        );
        Assert.assertTrue(found, "Customer not found: " + expectedCustomer);
    }

    /**
     * Finds a customer in the customer list and returns their index
     * @param customersPage The CustomersPage instance
     * @param firstName First name to search for
     * @param lastName Last name to verify
     * @param postcode Postcode to verify
     * @return Index of the matching customer, or -1 if not found
     */
    public static int findCustomerIndex(CustomersPage customersPage, String firstName, String lastName, String postcode) {
        // Search by first name
        customersPage.searchByFirstName(firstName);
        
        // Get all customers after search
        List<Map<String, String>> customers = customersPage.getAllCustomers();
        
        // Find matching customer
        for (int i = 0; i < customers.size(); i++) {
            Map<String, String> customer = customers.get(i);
            if (customer.get("FirstName").equals(firstName) &&
                customer.get("LastName").equals(lastName) &&
                customer.get("Postcode").equals(postcode)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Deletes a customer if found
     * @param customersPage The CustomersPage instance
     * @param firstName First name to search for
     * @param lastName Last name to verify
     * @param postcode Postcode to verify
     * @return true if customer was found and deleted, false otherwise
     */
    public static boolean deleteCustomer(CustomersPage customersPage, String firstName, String lastName, String postcode) {
        int customerIndex = findCustomerIndex(customersPage, firstName, lastName, postcode);
        
        if (customerIndex >= 0) {
            customersPage.deleteCustomer(customerIndex);
            return true;
        }
        
        return false;
    }
    
    /**
     * Verifies if a customer exists in the list
     * @param customersPage The CustomersPage instance
     * @param firstName First name to search for
     * @param lastName Last name to verify
     * @param postcode Postcode to verify
     * @return true if customer exists, false otherwise
     */
    public static boolean customerExists(CustomersPage customersPage, String firstName, String lastName, String postcode) {
        customersPage.searchByFirstName(firstName);
        List<Map<String, String>> customers = customersPage.getAllCustomers();
        
        return customers.stream().anyMatch(customer ->
            customer.get("FirstName").equals(firstName) &&
            customer.get("LastName").equals(lastName) &&
            customer.get("Postcode").equals(postcode)
        );
    }
} 