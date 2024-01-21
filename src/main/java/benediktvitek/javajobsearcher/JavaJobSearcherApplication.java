package benediktvitek.javajobsearcher;
import benediktvitek.javajobsearcher.utils.parsers.JobStackResponseParser;
import benediktvitek.javajobsearcher.utils.webscrapers.JobStackScraper;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

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
