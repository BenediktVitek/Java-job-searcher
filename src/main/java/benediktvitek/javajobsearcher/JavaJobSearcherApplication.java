package benediktvitek.javajobsearcher;

import benediktvitek.javajobsearcher.Utils.Parsers.JobStackResponseParser;
import benediktvitek.javajobsearcher.Utils.WebScrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.JobStackScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaJobSearcherApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JavaJobSearcherApplication.class, args);


//        GlassDoorsWebScraper glass = new GlassDoorsWebScraper();
//        List<String> positions = glass.getParsedResponse();
//        System.out.println(positions);
        JobStackScraper jobStack = new JobStackScraper("https://www.jobstack.it/it-jobs?keywords=junior%20java%20developer&isDetail=1",
                new JobStackResponseParser());

        jobStack.getParsedResponseTest(new ArrayList<>(), "https://www.jobstack.it/it-jobs?keywords=junior%20java%20developer&isDetail=1");
    }

}
