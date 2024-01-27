package benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers;

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

    public IndeedWebScraper(@Value("${indeed.site.url}") String url) {
        super(url);
    }

    @Override
    public List<String> getJobOffers() {


        List<WebElement> offerLinks = webDriver.findElements(By.cssSelector(".css-5lfssm.eu4oa1w0"));
        List<String> description = new ArrayList<>();
        List<WebElement> elementsCookies = webDriver.findElements(By.id("onetrust-policy-text"));
        if (!elementsCookies.isEmpty()) {
            WebElement button = webDriver.findElement(By.id("onetrust-reject-all-handler"));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
        }
        for (WebElement singleOffer : offerLinks) {
            singleOffer.click();
            description.add(webDriver.findElement(By.id("jobDescriptionText")).getText());

        }
        return description;
    }
}
