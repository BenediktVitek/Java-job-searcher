package benediktvitek.javajobsearcher.utils.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobStackResponseParser extends ResponseParser {

    private final String DOMAIN = "https://www.jobstack.it";

    @Override
    public String buildMessage(String offer, String path) {
        Document document = Jsoup.parse(offer);
        return document.select(".jobpost-h1-2023").text().concat("\n" + DOMAIN + path);
    }

    @Override
    public boolean isSuitable(String offer) {

        if (offer == null || offer.isEmpty()) {
            return false;
        }

        String responseParts = "";
        Document document = Jsoup.parse(offer);
        Elements headerLabels = document.select(".jobpost-values-2023");
        Elements description = document.select(".custom-profile-intro-box.custom-profile-intro-box--left.jobpost-description-2023");
        responseParts = responseParts.concat(headerLabels.text()).concat(description.text());

        return responseParts.matches(".*\\b[Jj]ava[^a-zA-Z].*[Jj]unior\\b.*|.*\\b[Jj]unior[^a-zA-Z].*[Jj]ava\\b.*");
    }

    public String getNextPage(String pageView) {
        Document document = Jsoup.parse(pageView);
        Elements nextPageLink = document.select("#page_next a");
        if(nextPageLink.isEmpty()) {
            return null;
        }
        return nextPageLink.get(0).attr("href");
    }

    public List<String> getPageOfferLinks(String pageView) {
        List<String> offerLinks = new ArrayList<>();
        Document document = Jsoup.parse(pageView);
        Elements hrefs = document.select(".jobpost-mainlink");
        for (Element link : hrefs) {
            offerLinks.add(link.attr("href"));
        }
        return offerLinks;
    }
}
