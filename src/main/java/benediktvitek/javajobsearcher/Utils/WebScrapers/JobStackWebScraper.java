package benediktvitek.javajobsearcher.utils.webscrapers;

import benediktvitek.javajobsearcher.services.JobStackOfferService;
import benediktvitek.javajobsearcher.utils.parsers.JobStackResponseParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobStackWebScraper extends HttpClientWebScraper {

    private final JobStackOfferService jobStackOfferService;
    private final JobStackResponseParser jobStackResponseParser;

    public JobStackWebScraper(@Value("${jobstack.site.url}") String url,
                              JobStackOfferService jobStackOfferService,
                              JobStackResponseParser jobStackResponseParser) {
        super(url, jobStackResponseParser);
        this.jobStackOfferService = jobStackOfferService;
        this.jobStackResponseParser = jobStackResponseParser;
    }

    private List<String> getOfferLinks(String url) {
        List<String> offers = new ArrayList<>();
        String pageView = scrapePage(url);
        offers.addAll(jobStackResponseParser.getPageOfferLinks(pageView));
        String nextPageLink = jobStackResponseParser.getNextPage(pageView);
        if (nextPageLink != null) {
            offers.addAll(getOfferLinks("https://www.jobstack.it" + nextPageLink));
        }
        return offers;
    }
    @Override
    public List<String> getJobOffers() {
        List<String> offerLinks = getOfferLinks(URL);
        List<String> newOffers = jobStackOfferService.saveAndGetNewOffers(offerLinks);
        List<String> validOffers = new ArrayList<>();
        for (String offerUrl : newOffers) {
            String parsedOffer = scrapePage(offerUrl);
            if (jobStackResponseParser.isSuitable(parsedOffer)) {
                String message = jobStackResponseParser.buildMessage(parsedOffer, offerUrl);
                validOffers.add(message);
            }
        }
        if (validOffers.isEmpty()) {
            validOffers.add("No new offers found");
        }
        return validOffers;
    }
}