package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@api",
        features = "src/test/resources/features",
        glue = "stepDefinitions"
//        ,dryRun = true

        , plugin = {

                "pretty",
                "html:target/built-in-html-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed-scenarios.txt"

                   },
        stepNotifications = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE

)





public class CucumberRunner {
}
