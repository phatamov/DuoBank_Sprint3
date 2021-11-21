@database
Feature: Database tests related scenarios

  @db_only @regression
  Scenario: Verify the user and relater information from database
    When I sent a query to database for user`s and realtor`s information
    """
    select * from tbl_mortagage where id = '564'
    """
    Then The user`s and realtor`s information should match

  @db_only @regression
  Scenario: Updated the user and relater information in the database
    When I sent update query to the database for user`s and realtor`s updated information
    """
    update tbl_mortagage set realtor_info='Bruce Lee',
    b_firstName='Indiana', b_lastName='Jones',
    b_email='ijones@gmail.com' where id='562'
    """
    Then The user`s and realtor`s information should be updated

  @db_only @regression
  Scenario: Verify duplicated SSN Numbers in the database
    When I retrieve SSN column from the database
    """
    select b_ssn from tbl_mortagage
    """
    Then There should not be duplicates

  @db_only @regression
  Scenario: Verify the first 5 users in users table
    When I send a query to get the first 5 users from users table
    Then The result should be the following
      | 3323 | test@test.com        | df5ea29924d39c3be8785734f13169c6 | Tester | Testing |
      | 3324 | team_duo10@gmail.com | e646c1e42aff12b4e1cff2fd8819da0d | Team   | Duo10   |
      | 3325 | duo10_team@gmail.com | 099a52d6ed14183b8a02cbe62e980d2c | Duo10  | Team    |
      | 3326 | tester@gmail.com     | 7f2ababa423061c509f4923dd04b6cf1 | Tester | Tester  |
      | 3327 | mickey@gmail.com     | fad85e1613f298102d543545c0cc1187 | Buddy  | Schultz |

  @db_only @regression
  Scenario: Verify selected row information in mortgage table
    When I send a query to get the selected applicant from mortgage table
    Then The result should as below
      | Deandre Carroll Jr. | Purchase a Home | Deloras | Haag | tameika.dietrich@gmail.com | 562-84-5820 |