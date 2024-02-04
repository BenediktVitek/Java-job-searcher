package benediktvitek.javajobsearcher.Utils.WebScrapers.factories;

import benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers.CocumaWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers.JobStackWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers.JobsCzWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers.IndeedWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WebScraperFactory {

    private final JobStackWebScraper jobStackWebScraper;
    private final GlassDoorsWebScraper glassDoorsWebScraper;

    private final JobsCzWebScraper jobsCzWebScraper;

    private final IndeedWebScraper indeedWebScraper;

    private final CocumaWebScraper cocumaWebScraper;
    private final List<WebScraper> webScrapers = new ArrayList<>();


    @PostConstruct
    private void initializeWebScrapers() {
        webScrapers.add(jobStackWebScraper);
        webScrapers.add(glassDoorsWebScraper);
        webScrapers.add(jobsCzWebScraper);
        webScrapers.add(indeedWebScraper);
        webScrapers.add(cocumaWebScraper);
    }
    public List<WebScraper> getWebScrapers() {
        return new ArrayList<>(webScrapers);
    }

}
