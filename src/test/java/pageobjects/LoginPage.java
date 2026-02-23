package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ===============================
    // Elements
    // ===============================

    @FindBy(xpath = "//input[@name='userEmail']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginBtn;

    @FindBy(xpath = "//small[contains(@class,'validation')]")
    private WebElement validationMessage;

    // ===============================
    // Actions (Using BasePage methods)
    // ===============================

    public void enterUserName(String username) {
        type(userName, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickOnLogin() {
        click(loginBtn);
    }

    // ===============================
    // Dynamic Error Message Handling
    // ===============================

    public String getLoginErrorMessage(String expectedText) {

        By dynamicErrorLocator =
                By.xpath("//*[contains(text(),'" + expectedText + "')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dynamicErrorLocator)
        ).getText();
    }



    public boolean isLoginPageLoaded() {
        By usernameField = By.xpath("//input[@name='userEmail']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        return true;
    }
}