package benediktvitek.javajobsearcher.utils.webscrapers.factories;

import benediktvitek.javajobsearcher.utils.webscrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.JobStackScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.WebScraper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebScraperFactory {

    @Value("${jobstack.site.url}")
    private String jobStackUrl;
    @Value("${glassdoors.site.url}")
    private String glassDoorsUrl;

    private List<WebScraper> webScrapers;

    public WebScraperFactory() {
        webScrapers = new ArrayList<>();
    }

    @PostConstruct
    private void initializeWebScrapers() {
        webScrapers.add(new JobStackScraper(jobStackUrl));
        webScrapers.add(new GlassDoorsWebScraper(glassDoorsUrl));
    }
    public List<WebScraper> getWebScrapers() {
        return new ArrayList<>(webScrapers);
    }

}
