package benediktvitek.javajobsearcher;
import benediktvitek.javajobsearcher.utils.parsers.JobStackResponseParser;
import benediktvitek.javajobsearcher.utils.webscrapers.JobStackScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.WebScraper;
import benediktvitek.javajobsearcher.utils.webscrapers.factories.WebScraperFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaJobSearcherApplication implements CommandLineRunner {

    private final WebScraperFactory webScraperFactory;
    public static void main(String[] args) {
        SpringApplication.run(JavaJobSearcherApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        List<WebScraper> webScrapers = webScraperFactory.getWebScrapers();
        for (WebScraper scraper: webScrapers) {
            scraper.getJobOffers();
        }
    }
}
