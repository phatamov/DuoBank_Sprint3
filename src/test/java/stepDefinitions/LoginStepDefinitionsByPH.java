package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class LoginStepDefinitionsByPH {

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }
    @When("I enter the valid credentials")
    public void i_enter_the_valid_credentials() {
        new LoginPage().login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    }
    @Then("I should able to login and land on the homepage")
    public void i_should_able_to_login_and_land_on_the_homepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/dashboard.php"));
    }

    @When("I am using invalid username {string} and invalid password {string}")
    public void i_am_using_invalid_username_and_invalid_password(String username, String password) {
        new LoginPage().login(username, password);
    }
    @Then("I should not able to login and get an error message")
    public void i_should_not_able_to_login_and_get_an_error_message() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(ConfigReader.getProperty("url")));
        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Login Failed"));
    }

    @When("I don`t enter any credentials")
    public void i_don_t_enter_any_credentials() {
        new LoginPage().login("", "");
    }
    @Then("I should not able to login and stay same url")
    public void i_should_not_able_to_login_and_stay_same_url() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals(ConfigReader.getProperty("url")));
    }

    @When("I enter valid username {string} and invalid password {string}")
    public void i_enter_valid_username_and_invalid_password(String username, String password) {
        new LoginPage().login(username, password);
    }

    @When("The user enters the valid credentials as {string} for username and {string} for password")
    public void the_user_enters_the_valid_credentials_as_for_username_and_for_password(String email, String password) {
        new LoginPage().login(email, password);
    }
    @Then("The user should be able to login and land on the homepage")
    public void the_user_should_be_able_to_login_and_land_on_the_homepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/dashboard.php"));
    }



}
