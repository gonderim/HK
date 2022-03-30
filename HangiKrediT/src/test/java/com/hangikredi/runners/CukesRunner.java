package com.hangikredi.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt",
                "pretty"        },
                stepNotifications = true,
                strict = true,
        features = "src/test/resources/features",
        glue = "com/hangikredi/stepDefs",
        dryRun = false,
        tags = "@smoke"
)
public class CukesRunner {
}
