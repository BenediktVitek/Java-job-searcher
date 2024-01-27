package benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers;

import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class SeleniumWebScraper extends WebScraper {

    protected WebDriverWait wait;
    protected WebDriver webDriver;

    public SeleniumWebScraper(String url) {
        super(url);

        initializeWebDriver();
    }

    private void initializeWebDriver() {

        webDriver = SeleniumWebDriverSingleton.getWebDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(URL);
    }
}
