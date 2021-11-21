package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;




    @RunWith(Cucumber.class)
    @CucumberOptions(

            tags = "@regression",

            features = "src/test/resources/features",
            glue = "stepDefinitions"


            , plugin = {

            "pretty",
            "html:target/built-in-html-report.html",
            "json:target/cucumber.json",

    },
            stepNotifications = true,
            snippets = CucumberOptions.SnippetType.CAMELCASE

    )





    public class ParallelRunner {
    }


