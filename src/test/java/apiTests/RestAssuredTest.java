package apiTests;

import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.sql.SQLOutput;
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class RestAssuredTest {

    @Test
    public void testRestAssured(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";

        String adminAut = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjMxODI2NDMzLCJleHAiOjE2MzE4MzAwMzMsImRhdGEiOnsidXNlcl9pZCI6IjM1NjgiLCJ0eXBlIjoiMSJ9fQ.0ZSV5XZIwt2SfdxeR6NftwOsEfkQEK7oY2AqO_YpNcw";
        given().
                header("Authorization", adminAut).
                header("Accept", "application/json").

                when().log().all().
                get("getmortagage.php").

                then().log().all().
                statusCode(200).
                body("mortagage_applications[1].b_firstName", equalTo("Thomas"));

    }

    @Test
    public void testRestAssured2(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";

        String path;
        path = "/login.php";

        Response response =

                given()
                        .auth()
                        .preemptive()
                        .basic("email", "password")
                        .header("Accept", ContentType.JSON.getAcceptHeader()) // "application/json"
                        .contentType(ContentType.JSON).
                        when().body(new File("C:\\Users\\parvi\\IdeaProjects\\Sprint4-DuoBank\\src\\test\\java\\apiTests\\payload.json"))
                        .post(path).
                        then().extract().response();

        Assert.assertEquals(200, response.getStatusCode());

//        given().
//                header("Accept", "application/json").
//                header("name", )
//
//                when().log().all().
//                get("getmortagage.php").
//
//                then().log().all().
//                statusCode(200).
//                body("mortagage_applications[1].b_firstName", equalTo("Thomas"));

    }
}
