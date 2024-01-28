package benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWebDriverSingleton {

    private static WebDriver webDriver;

    private SeleniumWebDriverSingleton() {
    }

    public static synchronized WebDriver getWebDriver() {
        if (webDriver == null) {
            initializeWebDriver();
        }
        return webDriver;
    }

    private static void initializeWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("-headless");
        webDriver = new FirefoxDriver(options);
    }

    public static synchronized void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}