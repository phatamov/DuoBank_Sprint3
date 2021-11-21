@Expenses
Feature: Operation on Expenses page.

  Background: steps for all scenarios
    Given I am on the homepage
    Then I enter the valid credentials
    Then I should able to login and land on the homepage
    Then I entering to Mortgage application
    Then I Entering Preapproval Details
    Then I Entering Personal Information
    Then I am at Expenses page

  @smoke @regression
  Scenario: Verify validation of Rent Check Box
    When I selecting the Rent checkbox
    Then I enter random digits in the Monthly Rental Payment field
    Then I go to next page

  @smoke @regression
  Scenario: Verify validation of Own Check Box
    When I selecting the Own checkbox
    Then I enter random digits in the First Mortagage Payment field
    Then I go to next page

  @smoke @regression #bug
  Scenario: Verify validation of First Mortagage Payment field
    When I selecting the Own checkbox
    Then I enter random, trillion value, digits in the First Mortgage Payment field
    Then I go to next page

  @smoke @regression #bug
  Scenario: Verify validation of Rental Payment field
    When I selecting the Rent checkbox
    Then I enter random, trillion value, digits in the Monthly Rental Payment field
    Then I go to next page
