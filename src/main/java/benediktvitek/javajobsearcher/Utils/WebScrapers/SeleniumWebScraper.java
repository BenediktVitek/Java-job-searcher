package benediktvitek.javajobsearcher.utils.webscrapers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class SeleniumWebScraper extends WebScraper {

    protected WebDriverWait wait;
    protected WebDriver webDriver;

    public SeleniumWebScraper(String url) {
        super(url);
        initializeWebDriver();
    }

    private void initializeWebDriver() {

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        webDriver = new FirefoxDriver(options);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(URL);
    }

    public void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
