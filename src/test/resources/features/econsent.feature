Feature: Econsent related features

  Background: steps for all scenarios
    Given I am on the homepage


   @db_only @failed @regression
   Scenario Outline: Sign Econsent using scenario outline

    When I fill up the fields with the following user information
      | First Name | Last Name | Email |
      | <firstName> | <lastName> | <email> |
    Then This information should be stored properly in the database

    Examples:
      | firstName | lastName | email          |
      | Zaur      | Guliyev  | zaur@gmail.com |
      | Nezz      | Sari     | nezz@gmail.com |

  @db_only @regression
  Scenario: Verify signed Econsent info in DB

    When I fill up the fields with the following user information

  | First Name | Last Name | Email
  | Nezz       | Sari      | nezz@gmail.com

    Then This information should be stored properly in the database

   @db_only @regression
   Scenario: Verify information from the database
  When I send the query to update econsent page first name, last name and email
  Then The actual output from the query should match the expected one that I sent to query









