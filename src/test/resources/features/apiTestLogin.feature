Feature: API Login Tests

  @api
  Scenario: API Scenario Test 1
    Given I add the headers "Accept" "application/json" and "Authorization" "AdminAut"
    When I send a GET request to "getmortagage.php" endpoint
    Then the status code should be 200
    And The list should contains firstname "Thomas" and "Domingo"

  @api
  Scenario: API Scenario Test 2
    Given I add the headers "Accept" "application/json"
    When I POST "payloadFile" to "/login.php" path
    Then I verify the status code should be 200