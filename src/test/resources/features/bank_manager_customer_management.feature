@feature
Feature: Bank Manager Actions
  As a Bank Manager
  I want to be able to add new customers and delete customer

  Background: Go to Login Page
    Given I am on the bank login page
    And I click Bank Manager Login button

  Scenario: Bank Manager Add and Delete Multiple Customers
    When I click Add Customer tab
    And I add multiple customers
      | FirstName   | LastName    | Postcode |
      | Christopher | Connely     | L789C349 |
      | Frank       | Christopher | A897N450 |
      | Christopher | Minka       | M098Q585 |
      | Connely     | Jackson     | L789C349 |
      | Jackson     | Frank       | L789C349 |
      | Minka       | Jackson     | A897N450 |
      | Jackson     | Connely     | L789C349 |
    And Go to Customers tab
    Then Verify newly added customers at Customer page
      | FirstName   | LastName    | Postcode |
      | Christopher | Connely     | L789C349 |
      | Frank       | Christopher | A897N450 |
      | Christopher | Minka       | M098Q585 |
      | Connely     | Jackson     | L789C349 |
      | Jackson     | Frank       | L789C349 |
      | Minka       | Jackson     | A897N450 |
      | Jackson     | Connely     | L789C349 |
    When I delete multiple customers
      | FirstName   | LastName    | Postcode |
      | Jackson     | Frank       | L789C349 |
      | Christopher | Connely     | L789C349 |
    Then Verify deleted customers at Customer page
      | FirstName   | LastName    | Postcode |
      | Jackson     | Frank       | L789C349 |
      | Christopher | Connely     | L789C349 |