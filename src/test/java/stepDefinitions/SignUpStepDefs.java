package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.DBUtility;
import utilities.Driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignUpStepDefs {


    String first;
    String last ;
    String email;
    String pass ;
    String expectedPassword;

    @Given("I am in homepage and click on sign up")
    public void iAmInHomepageAndClickOnSignUp() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        new LoginPage().signUpLink.click();
    }
    @When("I fill up the fields with the following new user information")
    public void iFillUpTheFieldsWithTheFollowingNewUserInformation(List<Map<String, String>> dataTable) throws SQLException {

        first = dataTable.get(0).get("First Name");
        last = dataTable.get(0).get("Last Name");
        email = dataTable.get(0).get("Email");
        pass = dataTable.get(0).get("Password");
        expectedPassword = DigestUtils.md5Hex(pass);

        SignUpPage signUpPage = new SignUpPage();

        signUpPage.firstName.sendKeys(first);
        signUpPage.lastName.sendKeys(last);
        signUpPage.email.sendKeys(email);
        signUpPage.password.sendKeys(pass);
        signUpPage.registerButton.click();


    }
    @Then("This information should be stored correctly in the database")
    public void thisInformationShouldBeStoredCorrectlyInTheDatabase() throws SQLException {


        String query = "select first_name, last_name, email, password from tbl_user where email = '"+email+"'";


        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> map = queryResultListOfMaps.get(0);

        System.out.println(map);

        Assert.assertEquals(first, (String)(map.get("first_name")));
        Assert.assertEquals(last, (String)(map.get("last_name")));
        Assert.assertEquals(email, ((String)(map.get("email"))).toLowerCase());
        Assert.assertEquals(expectedPassword, (String)(map.get("password")));


        DBUtility.updateQuery("delete from tbl_user where email='"+email+"'");

    }

    @When("I fill up the email field with invalid email format")
    public void iFillUpTheEmailFieldWithInvalidEmailFormat() {
        SignUpPage signUpPage = new SignUpPage();
        Faker fake = new Faker();

        signUpPage.firstName.sendKeys(fake.name().firstName());
        signUpPage.lastName.sendKeys(fake.name().lastName());
        signUpPage.email.sendKeys(fake.name().firstName());
        signUpPage.registerButton.click();


    }
    @Then("I should not be able to sign up and I should get an error message")
    public void iShouldNotBeAbleToSignUpAndIShouldGetAnErrorMessage() {
        SignUpPage signUpPage = new SignUpPage();
        WebElement inputEmail = signUpPage.email;

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        boolean requiredEmailAddressErrorMessage = (Boolean) js.executeScript("return arguments[0].required;", inputEmail);
        Assert.assertTrue(requiredEmailAddressErrorMessage);
}

    @When("I try to sign up without entering any data")
    public void iTryToSignUpWithoutEnteringAnyData() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.registerButton.click();
    }

    @Then("I should not be able to sign up and an error message should be displayed")
    public void iShouldNotBeAbleToSignUpAndAnErrorMessageShouldBeDisplayed() {
        SignUpPage signUpPage = new SignUpPage();
        List<WebElement> signUpTable = new ArrayList<>();
        signUpTable.add(signUpPage.firstName);
        signUpTable.add(signUpPage.lastName);
        signUpTable.add(signUpPage.email);
        signUpTable.add(signUpPage.password);

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        boolean isRequired;

        for (int i = 0; i < signUpTable.size(); i++) {

            WebElement inputElement = signUpTable.get(i);
            isRequired = (Boolean) js.executeScript("return arguments[0].required;", inputElement);
            Assert.assertTrue(isRequired);
        }
    }


}
