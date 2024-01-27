package benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.JobsCzResponseParser;
import benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers.HttpClientWebScraper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JobsCzWebScraper extends HttpClientWebScraper {

    private final JobsCzResponseParser jobsCzResponseParser;

    public JobsCzWebScraper(@Value("${jobscz.site.url}") String url, JobsCzResponseParser responseParser) {
        super(url, responseParser);
        this.jobsCzResponseParser = responseParser;
    }

    @Override
    protected List<String> getOfferLinks(String url) {
        String pageView = getPageView(url);
        return jobsCzResponseParser.getPageOfferLinks(pageView);
    }

    @Override
    public List<String> getJobOffers() {
        List<String> offerLinks = getOfferLinks(URL);
        List<String> builtJobOffers = new ArrayList<>();
        for (String link : offerLinks) {
            String pageView = getPageView(link);
            if(jobsCzResponseParser.isSuitable(pageView)) {
                builtJobOffers.add(jobsCzResponseParser.buildMessage(pageView, link));
            }
        }
        if(builtJobOffers.isEmpty()) {
            builtJobOffers.add("No new offers found: https://www.jobs.cz");
        }
        return builtJobOffers;
    }


}
