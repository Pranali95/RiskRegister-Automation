
	package pageobjects;

	import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openqa.selenium.support.ui.ExpectedConditions;

	public class BasePage {

	    protected WebDriver driver;
	    protected WebDriverWait wait;

	    public BasePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    protected void click(WebElement element) {

	        wait.until(ExpectedConditions.visibilityOf(element));

	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(element));
	            element.click();
	        } catch (Exception e) {
	            ((JavascriptExecutor) driver)
	                    .executeScript("arguments[0].click();", element);
	        }
	    }

	    protected void type(WebElement element, String text) {
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.clear();
	        element.sendKeys(text);
	    }

	    protected String getText(WebElement element) {
	        wait.until(ExpectedConditions.visibilityOf(element));
	        return element.getText();
	    }

	    protected boolean isDisplayed(WebElement element) {
	        wait.until(ExpectedConditions.visibilityOf(element));
	        return element.isDisplayed();
	    }
	}
