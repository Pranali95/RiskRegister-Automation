package stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverManager;
import utilities.ScreenshotUtil;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        logger.info("Browser Launched Successfully");
    }
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        DriverManager.quitDriver();
    }}



/*What This Does
 * step 4: Interview Answer (Very Important)

If interviewer asks:

"How do you handle failures in your framework?"

You answer:

"I implemented a failure-handling mechanism using Cucumber Hooks. Whenever a scenario fails, a screenshot is automatically captured using TakesScreenshot and saved with a timestamp. This helps in debugging and root cause analysis. Logging is also integrated using Log4j2."

That answer = Senior level.
 */

/*If test fails:

Takes screenshot

Attaches inside Cucumber report

Automatically visible in HTML report

Interview gold point 🔥*/