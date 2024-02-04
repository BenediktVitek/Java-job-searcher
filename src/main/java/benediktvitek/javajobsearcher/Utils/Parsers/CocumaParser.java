package benediktvitek.javajobsearcher.Utils.Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CocumaParser extends ResponseParser {

    public List<String> getOfferLinks(String pageView) {
        List<String> offerLinks = new ArrayList<>();
        Document document = Jsoup.parse(pageView);
        Elements hrefs = document.select(".job-thumbnail.mb-4");
        for (Element link : hrefs) {
            offerLinks.add(link.attr("href"));
        }
        return offerLinks;
    }

    @Override
    public String buildMessage(String offer, String url) {
        return null;
    }

    @Override
    public boolean isSuitable(String offer) {
        return false;
    }

    public String getNextPage(String pageView) {
        Document document = Jsoup.parse(pageView);
        Elements nextPageLink = document.select("a[aria-label=Další]");
        if(nextPageLink.isEmpty()) {
            return null;
        }
        return nextPageLink.get(0).attr("href");
    }
}
