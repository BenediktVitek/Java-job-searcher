package benediktvitek.javajobsearcher.utils.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JobsCzResponseParser extends ResponseParser{



    @Override
    public String buildMessage(String offerView, String url) {

        Document document = Jsoup.parse(offerView);
        String positionName = document.select(".JobDescriptionHeading").text();
        return positionName.concat("\n" + url);
    }

    @Override
    public boolean isSuitable(String offer) {
        if (offer == null || offer.isEmpty()) {
            return false;
        }
        String responseParts = "";
        Document document = Jsoup.parse(offer);
        Elements headerLabels = document.select(".JobDescriptionHeading");
        Elements description = document.select(".RichContent.mb-1400");
        responseParts = responseParts.concat(headerLabels.text()).concat(" " + description.text());

        return responseParts.matches(".*\\b[Jj]ava[^a-zA-Z].*[Jj]unior\\b.*|.*\\b[Jj]unior[^a-zA-Z].*[Jj]ava\\b.*");
    }

    public List<String> getPageOfferLinks(String pageView) {
        List<String> offers = new ArrayList<>();
        Document document = Jsoup.parse(pageView);
        Elements noOffersFound = document.select(".Alert.Alert--informative.Alert--center mt-800");
        if (!noOffersFound.isEmpty()) {
            offers.add("No new positions found");
            return offers;
        }

        int numberOfOffersFound = getNumberOfJobs(document.select(".typography-body-medium-text-regular.mb-0").text());

        Elements offerElements = document.select(".SearchResultCard__header");
        for (int i = 0; i < numberOfOffersFound; i++) {
            String href = offerElements.get(i).selectFirst("a").attr("href");
            offers.add(href);
        }
        return offers;
    }

    private int getNumberOfJobs(String infoAboutFoundJobs) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(infoAboutFoundJobs);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }
}
