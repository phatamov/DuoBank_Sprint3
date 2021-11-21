@login
Feature: As a user, I should be able to login using login page.

  Background: Common steps for all scenarios
    Given I am on the homepage

  @smoke @regression
  Scenario: Login using valid credentials
    When I enter the valid credentials
    Then I should able to login and land on the homepage

  @regression
  Scenario: Login using invalid credentials
    When I am using invalid username "InvalidUsername@gmail.com" and invalid password "InvalidPassword"
    Then I should not able to login and get an error message

  @regression
  Scenario: Login with no credentials
    When I don`t enter any credentials
    Then I should not able to login and stay same url

  @regression
  Scenario: Login with valid username and invalid password
    When I enter valid username "phatamov8@gmail.com" and invalid password "InvalidPassword"
    Then I should not able to login and stay same url

  @regression
  Scenario Outline: Login using valid credentials through examples
    When The user enters the valid credentials as "<email>" for username and "<password>" for password
    Then The user should be able to login and land on the homepage
    Examples: valid username and password list
      | email                 | password    |
      | phatamov8@gmail.com   | phatamov8   |
      | phatamov88@gmail.com  | TestPass123 |
      | phatamov888@gmail.com | TestPass123 |
      | parvizh@gmail.com     | TestPass123 |
      | cronaldo@gmail.com    | CRonaldo7   |
