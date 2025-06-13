@feature
Feature: User Making Transaction
  As User able to login, view dashboard, and perform transactions

  Background: Go to Login Page
    Given I am on the bank login page
    And I click Customer Login button

  Scenario: Customer perform transactions
    When I select "Hermoine Granger" from dropdown
    And I click Login button
    And I select "1003" from account dropdown
    And I perform following transactions
      | type     | amount |
      | Credit   | 50000  |
      | Debit    | 3000   |
      | Debit    | 2000   |
      | Credit   | 5000   |
      | Debit    | 10000  |
      | Debit    | 15000  |
      | Credit   | 1500   |
    And I Go to Transaction tab
    Then Verify transaction page
      | type     | amount |
      | Credit   | 50000  |
      | Debit    | 3000   |
      | Debit    | 2000   |
      | Credit   | 5000   |
      | Debit    | 10000  |
      | Debit    | 15000  |
      | Credit   | 1500   |
    And Go back to Account Dashboard
    And Verify account balance