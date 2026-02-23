package stepdefinitions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.LoginPage;
import utilities.ConfigReader;
import utilities.DriverManager;

public class LoginStep {

    private static final Logger logger = LogManager.getLogger(LoginStep.class);

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginStep() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
    }

    // ===============================
    // Background Step
    // ===============================

    @Given("User is on login page")
    public void user_is_on_login_page() {

        logger.info("Navigating to Login Page");

        driver.get(ConfigReader.getProperty("baseUrl"));

        Assert.assertTrue(loginPage.isLoginPageLoaded(),
                "Login page not loaded");

        logger.info("Login Page loaded successfully");
    }

    // ===============================
    // Actions
    // ===============================

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        logger.info("Entering username: {}", username);
        loginPage.enterUserName(username);
    }

    @And("user enters password {string}")
    public void user_enters_password(String password) {
        logger.info("Entering password");
        loginPage.enterPassword(password);
    }

    @And("user clicks on login button")
    public void user_clicks_on_login_button() {
        logger.info("Clicking on Login button");
        loginPage.clickOnLogin();
    }

    // ===============================
    // Positive Validation
    // ===============================

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for actual landing URL after login
        wait.until(ExpectedConditions.urlContains("welcome"));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("welcome"),
                "Login failed - User not navigated to Welcome page");

        logger.info("Login successful - User navigated to Welcome page");
    }
    // ===============================
    // Negative Validation - Error Message
    // ===============================

    @Then("User should see error message {string}")
    public void user_should_see_error_message(String expectedMessage) {

        String actualMessage = loginPage.getLoginErrorMessage(expectedMessage);

        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(),
                "Error message mismatch");
    }
    
}




/*
 * 
 * 
 * 2️⃣ What This Gives You (Interview Points)
 *“Explain your automation framework architecture.”

You say:

I built a hybrid Cucumber-TestNG framework using Page Object Model.
WebDriver instances are managed via ThreadLocal for parallel execution.
Logging is implemented using Log4j2 with SLF4J binding.
Failures capture screenshots automatically via Cucumber Hooks.
Failed scenarios are rerun using Cucumber rerun plugin.
Execution can be controlled via tags and command-line parameters for CI/CD integration.

That answer = Senior Automation Engineer.

You can now say:

"I implemented Log4j2 logging across step definitions and page classes. Logging captures test flow, input values, validation results, and failure points. Logs are written both to console and file for debugging and CI analysis."

That is a strong senior-level answer.

✅ 3️⃣ What Happens Now?

When you run test:

A log file will be generated (if log4j2.xml configured correctly):

logs/automation.log

Inside it you will see:

INFO  Navigating to Login Page
INFO  Entering username: testuser@mail.com
INFO  Clicking on Login button
INFO  Login successful - Dashboard loaded

That is professional framework behavior.*/