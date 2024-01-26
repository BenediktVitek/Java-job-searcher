package benediktvitek.javajobsearcher.utils.webscrapers;

import benediktvitek.javajobsearcher.utils.parsers.JobsCzResponseParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JobsCzWebScraper extends HttpClientWebScraper {


    public JobsCzWebScraper(@Value("${jobscz.site.url}") String url, JobsCzResponseParser responseParser) {
        super(url, responseParser);
    }

    public List<String> scrapeMainPage(String url) throws IOException {

        List<String> offers = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
            String response = httpClient.execute(httpGet, responseHandler);
            Document document = Jsoup.parse(response);
            Elements noOffersFound = document.select(".Alert.Alert--informative.Alert--center mt-800");
            if (!noOffersFound.isEmpty()) {
                offers.add("No new positions found");
                return offers;
            }

            int numberOfOffersFound = getNumberOfJobs(document.select(".typography-body-medium-text-regular.mb-0").text());

            Elements offerElements = document.select(".SearchResultCard__header");
            for (int i = 0; i < numberOfOffersFound; i++) {
                String href = offerElements.get(i).selectFirst("a").attr("href");
                String position = offerElements.get(i).text();
                offers.add(href + "\n" + position);
            }

            return offers;
        }
    }

    private int getNumberOfJobs(String infoAboutFoundJobs) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(infoAboutFoundJobs);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

    @Override
    public List<String> getJobOffers() throws IOException {
        return scrapeMainPage(URL);
    }
}
