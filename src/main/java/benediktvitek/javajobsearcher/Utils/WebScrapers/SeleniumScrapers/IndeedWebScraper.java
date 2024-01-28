package benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.IndeedResponseParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IndeedWebScraper extends SeleniumWebScraper {

    private final IndeedResponseParser indeedResponseParser;
    private final String OFFER_DESCRIPTION_CSS_ELEMENT = ".jobsearch-jobDescriptionText.jobsearch-JobComponent-description.css-10og78z.eu4oa1w0";

    public IndeedWebScraper(@Value("${indeed.site.url}") String url,
                            IndeedResponseParser indeedResponseParser) {
        super(url);
        this.indeedResponseParser = indeedResponseParser;
    }

    @Override
    public List<String> getJobOffers() {


        List<WebElement> offerLinks = webDriver.findElements(By.cssSelector(".css-5lfssm.eu4oa1w0"));
        List<String> validOffers = new ArrayList<>();
        if (!webDriver.findElements(By.id("onetrust-policy-text")).isEmpty()) {
            WebElement button = webDriver.findElement(By.id("onetrust-reject-all-handler"));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
        }
        for (WebElement singleOffer : offerLinks) {
            String description;
            wait.until(ExpectedConditions.elementToBeClickable(singleOffer));
            singleOffer.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OFFER_DESCRIPTION_CSS_ELEMENT)));
            description = webDriver.findElement(By.cssSelector(OFFER_DESCRIPTION_CSS_ELEMENT)).getText();
            if (indeedResponseParser.isSuitable(description)) {
                validOffers.add(indeedResponseParser.buildMessage(singleOffer.findElement(By.cssSelector(".css-dekpa.e37uo190")).getText(),
                        singleOffer.findElement(By.tagName("a")).getAttribute("href")));
            }
        }

        if(validOffers.isEmpty()) {
            validOffers.add("No new offers found: https://cz.indeed.com");
        }
        return validOffers;
    }
}
