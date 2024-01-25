package benediktvitek.javajobsearcher.services;

import benediktvitek.javajobsearcher.utils.webscrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.WebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.factories.WebScraperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapeService {

    private final WebScraperFactory webScraperFactory;

    public List<String> getAllOffers() throws IOException {
        List<String> offers = new ArrayList<>();

        List<WebScraper> scrapers = webScraperFactory.getWebScrapers();

        for (WebScraper scraper : scrapers) {
            offers.addAll(scraper.getJobOffers());
        }

        return offers;
    }

}
