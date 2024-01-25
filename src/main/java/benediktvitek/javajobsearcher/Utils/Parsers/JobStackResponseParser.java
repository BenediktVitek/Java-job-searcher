package benediktvitek.javajobsearcher.utils.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobStackResponseParser extends ResponseParser {

    private final String DOMAIN = "https://www.jobstack.it";

    @Override
    public String buildMessage(String offer, String path) {
        Document document = Jsoup.parse(offer);
        return document.select("jobpost-h2-2032\n").text().concat(DOMAIN + path);
    }


    @Override
    public boolean isSuitable(String offer) {

        String responseParts = "";
        Document document = Jsoup.parse(offer);
        Elements headerLabels = document.select("jobpost-values-2023");
        Elements description = document.select("custom-profile-intro-box custom-profile-intro-box--left jobpost-description-2023");
        responseParts = responseParts.concat(headerLabels.text()).concat(description.text());

        return responseParts.contains("Junior") && responseParts.contains("Java");
    }
}
