package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.Select;
import pages.ExpensesPage;
import pages.LoginPage;
import pages.PersonalInformationPage;
import pages.PreApprovalDetaisPage;
import utilities.DBUtility;


public class ExpencesPageStepDefs {

    String expectedName;
    Object actualName;
    ExpensesPage expensesPage = new ExpensesPage();
    PersonalInformationPage personalInformationPage = new PersonalInformationPage();
    Faker faker = new Faker();

    @When("I send a query to get the monthly rental payment in resent created application")
    public void i_send_a_query_to_get_the_monthly_rental_payment_in_resent_created_application() {
        System.out.println("Sending query");
        List<List<Object>> queryResultAsListOfList = DBUtility.getQueryResultAsListOfLists
                ("select * from loan.tbl_mortagage");
        expectedName = "Wonder Woman";
        actualName = queryResultAsListOfList.get(148).get(35);
    }

    @Then("The received resent monthly rental payment should match with the expected result")
    public void the_received_resent_monthly_rental_payment_should_match_with_the_expected_result() {
        System.out.println("Verify the result ");
        if (expectedName.equals(actualName)) {
            System.out.println("System is working perfect");
        } else {
            System.out.println("System needs to be fixed!");
        }
        System.out.println("expected name: " + expectedName);
        System.out.println("actual name:   " + actualName);
    }

    @Given("I am logged using {string} for username and {string} for password")
    public void i_am_logged_using_for_username_and_for_password(String string, String string2) {
    }

    @When("I retrieve  access to expenses page in the mortgage application")
    public void i_retrieve_access_to_expenses_page_in_the_mortgage_application() {
    }

    @When("I selecting the Rent checkbox")
    public void i_selecting_the_rent_checkbox() {
        if (!expensesPage.rentChekBox.isSelected()) {
            expensesPage.rentChekBox.click();
        }
    }

    @Then("I enter random digits in the Monthly Rental Payment field")
    public void i_enter_random_digits_in_the_monthly_rental_payment_field() {
        expensesPage.monthlyRentalPayment.sendKeys(faker.number().digits(4));
    }

    @Then("I go to next page")
    public void i_go_to_next_page() {
        expensesPage.nextButton.click();
    }

    @Then("I am at Expenses page")
    public void i_am_at_expenses_page() {
        personalInformationPage.nextButton.click();

    }

    @Then("I entering to Mortgage application")
    public void i_entering_to_mortgage_application() {
        LoginPage loginPage = new LoginPage();
        loginPage.mortgageApplicationMenu.click();
    }

    @Then("I Entering Preapproval Details")
    public void i_entering_preapproval_details() {
        PreApprovalDetaisPage preApprovalDetaisPage = new PreApprovalDetaisPage();
        if (!preApprovalDetaisPage.checkBoxrealtor1.isSelected()) {
            preApprovalDetaisPage.checkBoxrealtor1.click();
        }
        preApprovalDetaisPage.realtorInfo.sendKeys(faker.name().fullName());
        if (!preApprovalDetaisPage.checkBoxLoanOfficer1.isSelected()) {
            preApprovalDetaisPage.checkBoxLoanOfficer1.click();
        }
        preApprovalDetaisPage.estimatedPrice.sendKeys("800000");
        preApprovalDetaisPage.downPayment.sendKeys("400000");
        preApprovalDetaisPage.next.click();

    }

    @Then("I Entering Personal Information")
    public void i_entering_personal_information() {

        if (!personalInformationPage.coBorrowerNoCheckBox.isSelected()) {
            personalInformationPage.coBorrowerNoCheckBox.click();
        }
        personalInformationPage.firstName.sendKeys(faker.name().firstName());
        personalInformationPage.middleName.sendKeys(faker.name().firstName());
        personalInformationPage.lastName.sendKeys(faker.name().lastName());
        Select selectBoxSuffix = new Select(personalInformationPage.suffixDropDownList);
        selectBoxSuffix.selectByIndex((int) (1 + (Math.random() * 5)));
        personalInformationPage.email.sendKeys(faker.internet().emailAddress());
        personalInformationPage.dateOfBirth.sendKeys("01012000");
        personalInformationPage.ssn.sendKeys(faker.number().digits(9));
        Select selectBoxStatus = new Select(personalInformationPage.martialStatus);
        selectBoxStatus.selectByIndex((int) (1 + (Math.random() * 3)));
        personalInformationPage.cellPhone.sendKeys(faker.phoneNumber().cellPhone());
        personalInformationPage.homePhone.sendKeys(faker.phoneNumber().phoneNumber());
        if (!personalInformationPage.privacyPolicyCheckBox.isSelected()) {
            personalInformationPage.privacyPolicyCheckBox.click();
        }

    }

    @When("I selecting the Own checkbox")
    public void i_selecting_the_own_checkbox() {
        expensesPage.ownChekBox.click();
    }

    @Then("I enter random digits in the First Mortagage Payment field")
    public void i_enter_random_digits_in_the_first_mortagage_payment_field() {
        expensesPage.firstMortgageTotalPayment.sendKeys(faker.number().digits(6));

    }

    @Then("I enter random, trillion value, digits in the First Mortgage Payment field")
    public void i_enter_random_trillion_value_digits_in_the_first_mortgage_payment_field() {
        expensesPage.firstMortgageTotalPayment.sendKeys(faker.number().digits(12));
    }

    @Then("I enter random, trillion value, digits in the Monthly Rental Payment field")
    public void i_enter_random_trillion_value_digits_in_the_monthly_rental_payment_field() {
        expensesPage.monthlyRentalPayment.sendKeys(faker.number().digits(12));
    }
}