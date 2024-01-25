package benediktvitek.javajobsearcher.utils.webscrapers.factories;

import benediktvitek.javajobsearcher.utils.webscrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.JobStackScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.WebScraper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class WebScraperFactory {

    private final JobStackScraper jobStackScraper;
    private final GlassDoorsWebScraper glassDoorsWebScraper;
    private List<WebScraper> webScrapers = new ArrayList<>();


    @PostConstruct
    private void initializeWebScrapers() {
        webScrapers.add(jobStackScraper);
        webScrapers.add(glassDoorsWebScraper);
    }
    public List<WebScraper> getWebScrapers() {
        return new ArrayList<>(webScrapers);
    }

}
