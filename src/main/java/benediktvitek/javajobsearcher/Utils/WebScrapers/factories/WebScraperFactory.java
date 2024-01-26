package benediktvitek.javajobsearcher.utils.webscrapers.factories;

import benediktvitek.javajobsearcher.utils.webscrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.JobStackWebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.JobsCzWebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.WebScraper;
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
    private final List<WebScraper> webScrapers = new ArrayList<>();


    @PostConstruct
    private void initializeWebScrapers() {
        webScrapers.add(jobStackWebScraper);
        webScrapers.add(glassDoorsWebScraper);
        webScrapers.add(jobsCzWebScraper);
    }
    public List<WebScraper> getWebScrapers() {
        return new ArrayList<>(webScrapers);
    }

}
