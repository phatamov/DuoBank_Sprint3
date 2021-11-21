package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DBStepDefinitionsByPH {

    String expectedRealtorName = "Leif Denesik";
    String expectedFirstName = "Genevie";
    String expectedLastName = "Kassulke";
    String expectedEmail = "rosalie.kub@gmail.com";
    Map<String, Object> mapInfo;

    String expectedNewRealtorName = "Bruce Lee";
    String expectedNewFirstName = "Indiana";
    String expectedNewLastName = "Jones";
    String expectedNewEmail = "ijones@gmail.com";
    Map<String, Object> mapUpdatedInfo;

    List<String> ssnNumbers;
    List<List<Object>>  actualUsersResult;
    List<List<Object>>  actualApplicantResult;

    @When("I sent a query to database for user`s and realtor`s information")
    public void i_sent_a_query_to_database_for_user_s_and_realtor_s_information(String query) {
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);
        mapInfo = listOfMaps.get(0);
    }
    @Then("The user`s and realtor`s information should match")
    public void the_user_s_and_realtor_s_information_should_match() {
        Assert.assertEquals(mapInfo.get("realtor_info"), expectedRealtorName);
        Assert.assertEquals(mapInfo.get("b_firstName"), expectedFirstName);
        Assert.assertEquals(mapInfo.get("b_lastName"), expectedLastName);
        Assert.assertEquals(mapInfo.get("b_email"), expectedEmail);
    }

    @When("I sent update query to the database for user`s and realtor`s updated information")
    public void i_sent_update_query_to_the_database_for_user_s_and_realtor_s_updated_information(String updatedQuery) throws SQLException {
        DBUtility.updateQuery(updatedQuery);
        String selectedQuery = "select * from tbl_mortagage where id = '562'";
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(selectedQuery);
        mapUpdatedInfo = listOfMaps.get(0);
    }
    @Then("The user`s and realtor`s information should be updated")
    public void the_user_s_and_realtor_s_information_should_be_updated() {
        Assert.assertEquals(mapUpdatedInfo.get("realtor_info"), expectedNewRealtorName);
        Assert.assertEquals(mapUpdatedInfo.get("b_firstName"), expectedNewFirstName);
        Assert.assertEquals(mapUpdatedInfo.get("b_lastName"), expectedNewLastName);
        Assert.assertEquals(mapUpdatedInfo.get("b_email"), expectedNewEmail);
    }

    @When("I retrieve SSN column from the database")
    public void i_retrieve_ssn_column_from_the_database(String query) {
        List<List<Object>> listOfLists = DBUtility.getQueryResultAsListOfLists(query);
        ssnNumbers = new ArrayList<>();
        for (List<Object> e : listOfLists) {
            ssnNumbers.add((String) (e.get(0)));
        }
        Collections.sort(ssnNumbers);
    }
    @Then("There should not be duplicates")
    public void there_should_not_be_duplicates() {
        boolean noDuplicate = true;
        for (int i = 0; i < ssnNumbers.size() - 1; i++) {
            if (ssnNumbers.get(i).equals(ssnNumbers.get(i + 1))) {
                noDuplicate = false;
            }
        }
        Assert.assertTrue("There are duplicated SSN numbers in the list", noDuplicate);
    }

    @When("I send a query to get the first {int} users from users table")
    public void iSendAQueryToGetTheFirstUsersFromUsersTable(Integer count) {
        actualUsersResult = DBUtility.getQueryResultAsListOfLists("select id, email, password, first_name, last_name from tbl_user limit"+count+"");
    }

    @Then("The result should be the following")
    public void theResultShouldBeTheFollowing(List<List<Object>> expectedResult) {
        for (int i = 0; i < expectedResult.size(); i++) {
            for (int j = 0; j < expectedResult.get(i).size(); j++) {
                Assert.assertEquals(expectedResult.get(i).get(j), String.valueOf(actualUsersResult.get(i).get(j)));
            }
        }
    }

    @When("I send a query to get the selected applicant from mortgage table")
    public void i_send_a_query_to_get_the_selected_applicant_from_mortgage_table() {
        actualApplicantResult = DBUtility.getQueryResultAsListOfLists("select realtor_info, purpose_loan, b_firstName, b_lastName, b_email, b_ssn from tbl_mortagage where id='670'");

    }
    @Then("The result should as below")
    public void the_result_should_as_below(List<List<Object>> expectedResult) {
        for (int i = 0; i < expectedResult.size(); i++) {
            for (int j = 0; j < expectedResult.get(i).size(); j++) {
                Assert.assertEquals(expectedResult.get(i).get(j), String.valueOf(actualApplicantResult.get(i).get(j)));
            }
        }
    }




}
