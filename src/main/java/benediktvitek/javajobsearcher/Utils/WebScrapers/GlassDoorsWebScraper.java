package benediktvitek.javajobsearcher.Utils.WebScrapers;

import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GlassDoorsWebScraper extends SeleniumWebScraper {

    public GlassDoorsWebScraper(@Value("${glassdoors.site.url}") String url) {
        super(url);
    }

    @Override
    public List<String> getJobOffers() {
        Integer numberOfJobs = null;

        List<String> positionDetails = new ArrayList<>();

        //Closing web driver in case anything goes bad
        try {
            //Find out if there are no new jobs or small number of them. Unwanted job offers appear in that case
            List<WebElement> descriptionElements = webDriver.findElements(By.id("lrp-zrp-heading_Description"));


            if (!descriptionElements.isEmpty()) {
                numberOfJobs = getNumberOfJobs(descriptionElements.get(0).getText());
                if (numberOfJobs == 0) {
                    positionDetails.add("No new offers on: https://www.glassdoors.com ");
                    return positionDetails;
                }
            }

            List<WebElement> elements = webDriver.findElements(By.className("JobsList_jobListItem__JBBUV"));

            if (numberOfJobs == null) {
                positionDetails = loopUsingForEach(elements);
            } else {
                positionDetails = loopUsingForILoop(elements, numberOfJobs);
            }
        } finally {
            closeWebDriver();
        }

        return positionDetails;
    }


    private int getNumberOfJobs(String infoAboutFoundJobs) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(infoAboutFoundJobs);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

    private List<String> loopUsingForILoop(List<WebElement> jobOffersElements, int numberOfOffers) {

        List<String> jobOffers = new ArrayList<>();
        for (int i = 0; i < numberOfOffers; i++) {
            jobOffers.add(buildMessage(jobOffersElements.get(i)));
        }
        return jobOffers;
    }

    private List<String> loopUsingForEach(List<WebElement> jobOffersElements) {
        List<String> jobOffers = new ArrayList<>();
        for (WebElement offer : jobOffersElements) {
            if (offer.isDisplayed()) {
                jobOffers.add(buildMessage(offer));
            }
        }
        return jobOffers;
    }

    private String buildMessage(WebElement element) {
        return element.findElement(By.className("EmployerProfile_employerName__Xemli")).getText() + "\n" +
                element.findElement(By.className("JobCard_seoLink__WdqHZ")).getText() + "\n" +
                element.findElement(By.className("JobCard_seoLink__WdqHZ")).getAttribute("href") + "\n";
    }
}
