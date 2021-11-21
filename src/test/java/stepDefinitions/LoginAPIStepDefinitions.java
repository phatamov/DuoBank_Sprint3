package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class LoginAPIStepDefinitions {

    String adminAut = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjMxODI2NDMzLCJleHAiOjE2MzE4MzAwMzMsImRhdGEiOnsidXNlcl9pZCI6IjM1NjgiLCJ0eXBlIjoiMSJ9fQ.0ZSV5XZIwt2SfdxeR6NftwOsEfkQEK7oY2AqO_YpNcw";
    RequestSpecification requestSpecification;
    Response response;

    @Given("I add the headers {string} {string} and {string} {string}")
    public void i_add_the_headers_and(String accept, String jasonApp, String authorization, String autToken) {

        autToken = adminAut;

        requestSpecification = given().
                header(accept, jasonApp).
                header(authorization, autToken);
    }
    @When("I send a GET request to {string} endpoint")
    public void i_send_a_get_request_to_endpoint(String endPoint) {

        response = requestSpecification.when().log().all().
                get(endPoint);
    }
    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        response.then().log().all().
                statusCode(statusCode);
    }
    @Then("The list should contains firstname {string} and {string}")
    public void the_list_should_contains_firstname_and(String name1, String name2) {
        response.then().log().all().
                body("mortagage_applications.b_firstName", hasItems(name1, name2));
    }




    @Given("I add the headers {string} {string}")
    public void iAddTheHeaders(String accept, String appJson) {


        requestSpecification = given()
                .auth()
                .preemptive()
                .basic("email", "password")
                .header(accept, appJson);

    }
    @When("I POST {string} to {string} path")
    public void iPOSTToPath(String payLoadFile, String path) {

        payLoadFile = "C:\\Users\\parvi\\IdeaProjects\\Sprint4-DuoBank\\src\\test\\java\\apiTests\\payload.json";

        response = requestSpecification.when().body(new File(payLoadFile))
                .post(path);

    }
    @Then("I verify the status code should be {int}")
    public void iVerifyTheStatusCodeShouldBe(Integer expectedStatusCode) {

        response.then().extract().response();

        Integer actualStatusCode = response.getStatusCode();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);

    }

}
