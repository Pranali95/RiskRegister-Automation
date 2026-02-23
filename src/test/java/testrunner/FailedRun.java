package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "@target/failed_scenarios.txt",
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class FailedRun extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

/*How do you rerun failed scenarios?
How failed_scenarios.txt Gets Created

It is created by this plugin line in your main runner:

"rerun:target/failed_scenarios.txt"
✅ Step-by-Step Working
1️⃣ Cucumber Starts Execution

You run:

Run.java

Cucumber reads:

plugin = {
    "pretty",
    "html:target/cucumber-report.html",
    "json:target/cucumber.json",
    "rerun:target/failed_scenarios.txt"
}
2️⃣ During Execution

If a scenario fails:

Example:

Scenario: Invalid login

Cucumber captures:

src/test/resources/features/login.feature:25
3️⃣ Cucumber Writes Into File

Automatically creates:

target/failed_scenarios.txt

And writes:

src/test/resources/features/login.feature:25

You DO NOT create this file manually.

It is auto-generated.

🔎 Where To See It

After failure:

Go to:

Project → target → failed_scenarios.txt

If file is empty → no failures.

If file has entries → those scenarios failed.
You say:

"I use Cucumber’s rerun plugin to capture failed scenarios in a text file and created a dedicated runner class pointing to that file. 
This allows clean re-execution of only failed scenarios without polluting reports.



Execution Flow (Production Standard)
Step 1

Run Run.java

If anything fails → file gets created:

target/failed_scenarios.txt
Step 2

Run FailedRun.java

It will execute only failed scenarios.



"*/