package benediktvitek.javajobsearcher;

import benediktvitek.javajobsearcher.Utils.WebScrapers.SeleniumScrapers.SeleniumWebDriverSingleton;
import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.factories.WebScraperFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        List<WebScraper> webScrapers =  webScraperFactory.getWebScrapers();
        try{
            for (WebScraper scraper: webScrapers) {
                System.out.println(scraper.getJobOffers());
            }
        } finally {
            SeleniumWebDriverSingleton.closeWebDriver();
            System.exit(0);
        }
    }

}
