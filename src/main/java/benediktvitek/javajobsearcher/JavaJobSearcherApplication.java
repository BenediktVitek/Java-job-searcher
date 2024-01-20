package benediktvitek.javajobsearcher;

import benediktvitek.javajobsearcher.Repositories.TestModelRepository;
import benediktvitek.javajobsearcher.Utils.WebScrapers.GlassDoorsWebScraper;
import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaJobSearcherApplication implements CommandLineRunner {

    private final TestModelRepository testModelRepository;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JavaJobSearcherApplication.class, args);


        GlassDoorsWebScraper glass = new GlassDoorsWebScraper();
        List<String> positions = glass.getParsedResponse();

        System.out.println(positions);
    }

    @Override
    public void run(String... args) throws Exception {
        testModelRepository.save(new TestModel("Franta", 15));
    }
}
