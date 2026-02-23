package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = {"stepdefinitions"},
        monochrome = true,
       // tags = "@blankLogin",   // 👈 Run only this
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt",   // ✅ THIS IS RERUN
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class Run extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
/*🧠 What You Can Say in Interview Now

If interviewer asks:

❓ How do you handle parallel execution?

You say:

"I implemented ThreadLocal WebDriver to ensure thread safety.
 Each scenario gets an isolated driver instance. The Cucumber runner uses a parallel DataProvider and TestNG suite is configured with 
 thread-count."

❓ How do you validate page load?

You say:

"I avoid validating using page title. Instead, I wait for a critical element using explicit waits to ensure synchronization in dynamic SPA applications."

❓ How do you generate reports?

You say:

"I integrated ExtentReports and Cucumber HTML reporting. Reports are generated under target directory and include execution status, duration, and screenshots for failed scenarios."
 * 
 * 
 * 
 * 
 * 
 * What This Line Does
"rerun:target/failed_scenarios.txt"

When a test fails:

Cucumber automatically writes failed scenario paths into:

target/failed_scenarios.txt

Example content:

src/test/resources/features/login.feature:12
✅ Then Your FailedRun.java Uses That File
@CucumberOptions(
        features = "@target/failed_scenarios.txt",  // reads failed only
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class FailedRun extends AbstractTestNGCucumberTests {

Notice the @ before path — that tells Cucumber:

👉 Read scenarios from file.*/

/*
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        monochrome = true,
        dryRun = false,
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        }
)*/