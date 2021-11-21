Feature: Sign Up related features

  Background:
    Given I am in homepage and click on sign up

  @signup_db @db_only @regression
  Scenario: Sign up a new user
    When I fill up the fields with the following new user information

      | First Name | Last Name | Email           | Password     |
      | Giulia     | Alex      | galex@gmail.com | giuliaalex55 |


    Then This information should be stored correctly in the database



  @signup_db @db_only @regression
  Scenario Outline: Sign up a new user
  When I fill up the fields with the following new user information

    | First Name  | Last Name  | Email   | Password |
    | <firstName> | <lastName> | <email> |<password> |

  Then This information should be stored correctly in the database

  Examples:
    | firstName | lastName | email                        | password  |
    | Coco      | Chanel   | coco@gmail.com               | cococo    |
    | Hinda     | Ervine   | hervine9@liveinternet.ru     | SCNCof    |
    | Neille    | Scarre   | nscarrea@nymag.com           | aixi6RY   |
    | Sula      | Ledwidge | sledwidgeb@timesonline.co.uk | dcXjoMugZ |


  @signup_ui @regression
  Scenario: Sign up a new user with invalid email input
    When I fill up the email field with invalid email format
    Then I should not be able to sign up and I should get an error message


  @signup_ui @ui_only
  Scenario: Sign up a new user without entering requested data
    When I try to sign up without entering any data
    Then I should not be able to sign up and an error message should be displayed










