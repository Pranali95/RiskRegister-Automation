package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        // Prevent object creation
    }

    public static WebDriver getDriver() {

        if (driver.get() == null) {

            String browser = System.getProperty(
                    "browser",
                    ConfigReader.getProperty("browser")
            );

            boolean headless = Boolean.parseBoolean(
                    System.getProperty(
                            "headless",
                            ConfigReader.getProperty("headless")
                    )
            );

            WebDriver webDriver;

            switch (browser.toLowerCase()) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");

                    if (headless) {
                        chromeOptions.addArguments("--headless=new");
                    }

                    webDriver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            driver.set(webDriver);
        }

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}