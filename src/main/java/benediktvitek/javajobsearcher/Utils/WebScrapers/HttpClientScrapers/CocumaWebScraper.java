package benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.CocumaParser;
import benediktvitek.javajobsearcher.Utils.Parsers.ResponseParser;
import benediktvitek.javajobsearcher.services.CocumaOfferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CocumaWebScraper extends HttpClientWebScraper {


    private final CocumaOfferService cocumaOfferService;
    private final String DOMAIN = "https://www.cocuma.cz";

    private final CocumaParser cocumaParser = (CocumaParser) responseParser;

    public CocumaWebScraper(@Value("${cocuma.site.url}") String url,
                            CocumaParser cocumaParser,
                            CocumaOfferService cocumaOfferService) {
        super(url, cocumaParser);
        this.cocumaOfferService = cocumaOfferService;
    }

    @Override
    protected List<String> getOfferLinks(String url) {
        List<String> offers = new ArrayList<>();
        String pageView = getPageView(url);
        offers.addAll(cocumaParser.getOfferLinks(pageView));
        String nextPageLink = cocumaParser.getNextPage(pageView);
        if (nextPageLink != null) {
            offers.addAll(getOfferLinks(DOMAIN + nextPageLink));
        }
        return offers;
    }

    @Override
    public List<String> getJobOffers() {
        List<String> offers = getOfferLinks(URL);
        System.out.println(offers.size());
        return null;
    }
}
