package benediktvitek.javajobsearcher.Utils.WebScrapers;

import benediktvitek.javajobsearcher.Utils.Country;
import benediktvitek.javajobsearcher.Utils.URLBuilders.GlassDoorURLBuilder;
import benediktvitek.javajobsearcher.Utils.URLBuilders.URLBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlassDoorsWebScraper extends SeleniumWebScraper {

    public GlassDoorsWebScraper() {
        super(new GlassDoorURLBuilder("Java junior developer", Country.CZECH_REPUBLIC, 1).getURL());
    }

    @Override
    public List<String> getParsedResponse() {
        WebDriverManager.firefoxdriver().setup();
        Integer numberOfJobs = null;
        List<String> positionDetails = new ArrayList<>();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");

        WebDriver webDriver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(URL);


        //Find out if there are no new jobs or small number of them. Unwanted job offers appear in that case
        try {
            numberOfJobs = getNumberOfJobs(webDriver.findElement(By.id("lrp-zrp-heading_Description")).getText());
            if (numberOfJobs == 0) {
                positionDetails.add("No new positions on glass door found");
                return positionDetails;
            }
        } catch (NoSuchElementException ignored) {
        }

        //Take care of popup
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            acceptButton.click();
        } catch (TimeoutException ignored) {
        }


        List<WebElement> elements = webDriver.findElements(By.className("JobsList_jobListItem__JBBUV"));

        if (numberOfJobs == null) {
            positionDetails = loopUsingForEach(elements);
        } else {
            positionDetails = loopUsingForILoop(elements, numberOfJobs);
        }

        return positionDetails;
    }



    public int getNumberOfJobs(String infoAboutFoundJobs) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(infoAboutFoundJobs);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

    public List<String> loopUsingForILoop(List<WebElement> jobOffersElements, int numberOfOffers) {

        List<String> jobOffers = new ArrayList<>();
        for (int i = 0; i < numberOfOffers; i++) {
            jobOffers.add(buildMessage(jobOffersElements.get(i)));
        }
        return jobOffers;
    }

    public List<String> loopUsingForEach(List<WebElement> jobOffersElements) {
        List<String> jobOffers = new ArrayList<>();
        for (WebElement offer : jobOffersElements) {
            if (offer.isDisplayed()) {
                jobOffers.add(buildMessage(offer));
            }
        }
        return jobOffers;
    }

    public String buildMessage(WebElement element) {
        return element.findElement(By.className("EmployerProfile_employerName__Xemli")).getText() + "\n" +
                element.findElement(By.className("JobCard_seoLink__WdqHZ")).getText() + "\n" +
                element.findElement(By.className("JobCard_seoLink__WdqHZ")).getAttribute("href") + "\n";
    }
}
