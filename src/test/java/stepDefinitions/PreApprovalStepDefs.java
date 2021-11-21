package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.PreApprovalDetaisPage;
import utilities.ConfigReader;
import utilities.DBUtility;
import utilities.Driver;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PreApprovalStepDefs {

    List<String> expectedColumns;
    List<String> actualColumns;
    String query;
    String expectedRealtorInfo;
    String actualRealtorInfo;


    @Given("I am on the Preapproval Details Page")
    public void iAmOnThePreapprovalDetailsPage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        LoginPage loginPage = new LoginPage();

        do {
            loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        }
        while (!Driver.getDriver().getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/dashboard.php"));

        loginPage.mortgageApplicationMenu.click();

    }

    @When("I click next without entering the required information")
    public void iClickNextWithoutEnteringTheRequiredInformation() {
        PreApprovalDetaisPage preApprovalDetaisPage = new PreApprovalDetaisPage();
        preApprovalDetaisPage.next.click();

    }

    @Then("I should not proceed to the next page and error messages should be displayed in every required field")
    public void iShouldNotProceedToTheNextPageAndErrorMessagesShouldBeDisplayedInEveryRequiredField() {

        PreApprovalDetaisPage preApprovalDetaisPage = new PreApprovalDetaisPage();

        Assert.assertEquals(preApprovalDetaisPage.realtorRequired.getText(), "THIS FIELD IS REQUIRED.");
        Assert.assertEquals(preApprovalDetaisPage.estimatedPriceRequired.getText(), "THIS FIELD IS REQUIRED.");
        Assert.assertEquals(preApprovalDetaisPage.downPaymentRequired.getText(), "THIS FIELD IS REQUIRED.");
        Assert.assertEquals(preApprovalDetaisPage.downPaymentPercentRequired.getText(), "THIS FIELD IS REQUIRED.");

        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[@id='steps-uid-0-t-0']//span[.='current step: ']")).isEnabled());

    }

    @When("I complete all required fields")
    public void iCompleteAllRequiredFields() {
        PreApprovalDetaisPage preApprovalDetaisPage = new PreApprovalDetaisPage();
        Faker fake = new Faker();

        if (!preApprovalDetaisPage.checkBoxrealtor1.isSelected()) {
            preApprovalDetaisPage.checkBoxrealtor1.click();
        }

        preApprovalDetaisPage.realtorInfo.sendKeys(fake.name().fullName());

        if (!preApprovalDetaisPage.checkBoxLoanOfficer1.isSelected()) {
            preApprovalDetaisPage.checkBoxLoanOfficer1.click();
        }

        preApprovalDetaisPage.estimatedPrice.sendKeys("600000");
        preApprovalDetaisPage.downPayment.sendKeys("100000");

        preApprovalDetaisPage.next.click();


    }

    @Then("I should be able to proceed to the next page")
    public void iShouldBeAbleToProceedToTheNextPage() {

        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[@id='steps-uid-0-t-1']//span[.='current step: ']")).isEnabled());

    }

    @When("I send a query to database to retrieve the names of columns in the Preapproval Page table")
    public void iSendAQueryToDatabaseToRetrieveTheNamesOfColumnsInThePreapprovalPageTable() {
        expectedColumns = Arrays.asList("id", "realtor_status", "realtor_info", "loan_officer_status", "purpose_loan", "est_purchase_price",
                "down_payment", "down_payment_percent", "total_loan_amount", "src_down_payment", "add_fund_available");

        actualColumns = DBUtility.getColumnNames("select id, realtor_status, realtor_info, loan_officer_status, purpose_loan, est_purchase_price, down_payment, down_payment_percent, total_loan_amount, \n" +
                "src_down_payment, add_fund_available from tbl_mortagage limit 1");
    }

    @Then("The retrieved columns should be similar to the expected columns")
    public void theRetrievedColumnsShouldBeSimilarToTheExpectedColumns() {
        Assert.assertEquals(actualColumns, expectedColumns);

    }

    @When("I send query to update the field with the number outside of the int range")
    public void iSendQueryToUpdateTheFieldWithTheNumberOutsideOfTheIntRange() {
        query = "update tbl_mortagage set loan_officer_status ='300000000000000' where id='314'";

    }

    @Then("The database should throw an exception")
    public void theDatabaseShouldThrowAnException() {
        try{
            DBUtility.updateQuery(query);
            Assert.assertTrue(false);
        }catch(Exception exception1){
            Assert.assertTrue(true);
        }
    }


    @When("I send query to update the filed with the non int value")
    public void iSendQueryToUpdateTheFiledWithTheNonIntValue() {
        query = "update tbl_mortagage set loan_officer_status ='abc' where id='314'";

    }

    @When("I send a query to update realtor info field with unicode value")
    public void iSendAQueryToUpdateRealtorInfoFieldWithUnicodeValue() throws SQLException {
        expectedRealtorInfo = "黄 麗";
        query = "update tbl_mortagage set realtor_info ='"+expectedRealtorInfo+"' where id='315'";
        DBUtility.updateQuery(query);

        Map<String, Object> map = DBUtility.getQueryResultListOfMaps("select * from tbl_mortagage where id = '315'").get(0);
        actualRealtorInfo = (String)(map.get("realtor_info"));


    }

    @Then("The database should update the entry")
    public void theDatabaseShouldUpdateTheEntry() {

        Assert.assertEquals(actualRealtorInfo, expectedRealtorInfo);

      }


    @When("I send query to update the primary key column with null value")
    public void iSendQueryToUpdateThePrimaryKeyColumnWithNullValue() {
        query = "update tbl_mortagage set id = null where id = '315'";

    }
}
