package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "@Test",
        plugin = {
                "pretty",
                "html:build/reports/cucumber/html-report.html",
                "json:build/reports/cucumber/cucumber.json",
                "junit:build/reports/cucumber/junit-report.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        publish = false // Disable publishing to cucumber.io
)
public class TestRunner {
        
}
