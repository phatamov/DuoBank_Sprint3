Feature: PreApproval Page related features

  Background:
    Given I am on the Preapproval Details Page

  @preapproval_ui  @ui_only @regression
  Scenario: Click "next" button without competing all required fields
  When I click next without entering the required information
  Then I should not proceed to the next page and error messages should be displayed in every required field


  @preapproval_ui  @ui_only @regression
  Scenario: Completing all the required information to proceed to the next page
    When I complete all required fields
    Then I should be able to proceed to the next page


  @preapproval_db @db_only @regression
  Scenario: Verify mortgage table Preapproval Details columns
    When I send a query to database to retrieve the names of columns in the Preapproval Page table
    Then The retrieved columns should be similar to the expected columns


    @preapproval_db @db_only @regression
    Scenario: Verifying that "realtor status" filed range is within int
      When I send query to update the field with the number outside of the int range
      Then The database should throw an exception

     @preapproval_db @db_only @regression
     Scenario: Verify that the "realtor status" filed type accepts only int values
        When I send query to update the filed with the non int value
        Then The database should throw an exception


      @preapproval_db @db_only @regression
      Scenario: Verify that the database supports unicode entries
         When I send a query to update realtor info field with unicode value
         Then The database should update the entry


      @preapproval_db @db_only @regression
      Scenario: Verify that the database throws exception if the primary key column is updated with null value
        When I send query to update the primary key column with null value
        Then The database should throw an exception

