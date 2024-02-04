package benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.JobStackResponseParser;
import benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers.HttpClientWebScraper;
import benediktvitek.javajobsearcher.services.JobStackOfferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobStackWebScraper extends HttpClientWebScraper {

    private final JobStackOfferService jobStackOfferService;
    private final JobStackResponseParser jobStackResponseParser = (JobStackResponseParser) responseParser;

    private final String DOMAIN = "https://www.jobstack.it";

    public JobStackWebScraper(@Value("${jobstack.site.url}") String url,
                              JobStackOfferService jobStackOfferService,
                              JobStackResponseParser jobStackResponseParser) {
        super(url, jobStackResponseParser);
        this.jobStackOfferService = jobStackOfferService;
    }

    protected List<String> getOfferLinks(String url) {
        List<String> offers = new ArrayList<>();
        String pageView = getPageView(url);
        offers.addAll(jobStackResponseParser.getPageOfferLinks(pageView));
        String nextPageLink = jobStackResponseParser.getNextPage(pageView);
        if (nextPageLink != null) {
            offers.addAll(getOfferLinks(DOMAIN + nextPageLink));
        }
        return offers;
    }
    @Override
    public List<String> getJobOffers() {
        List<String> offerLinks = getOfferLinks(URL);
        List<String> newOffers = jobStackOfferService.saveAndGetNewOffers(offerLinks);
        List<String> validOffers = new ArrayList<>();
        for (String offerUrl : newOffers) {
            String parsedOffer = getPageView(DOMAIN + offerUrl);
            if (jobStackResponseParser.isSuitable(parsedOffer)) {
                String message = jobStackResponseParser.buildMessage(parsedOffer, offerUrl);
                validOffers.add(message);
            }
        }
        if (validOffers.isEmpty()) {
            validOffers.add("No new offers found: " + DOMAIN);
        }
        return validOffers;
    }
}