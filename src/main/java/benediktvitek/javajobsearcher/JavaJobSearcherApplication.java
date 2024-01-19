package benediktvitek.javajobsearcher;

import benediktvitek.javajobsearcher.Utils.WebScrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class JavaJobSearcherApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JavaJobSearcherApplication.class, args);

        GlassDoorsWebScraper glass = new GlassDoorsWebScraper();
        List<String> positions = glass.getParsedResponse();

        System.out.println(positions);
    }

}
