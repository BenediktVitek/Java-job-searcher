package benediktvitek.javajobsearcher.utils.webscrapers;

import benediktvitek.javajobsearcher.entities.JobStackOffer;
import benediktvitek.javajobsearcher.repositories.JobStackOfferRepository;
import benediktvitek.javajobsearcher.utils.parsers.JobStackResponseParser;
import benediktvitek.javajobsearcher.utils.parsers.ResponseParser;
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
import java.util.stream.Collectors;

@Component
public class JobStackScraper extends HttpClientWebScraper {

    private final JobStackOfferRepository jobStackOfferRepository;
    private final JobStackResponseParser jobStackResponseParser;

    public JobStackScraper(@Value("${jobstack.site.url}") String url, ResponseParser responseParser,
                           JobStackOfferRepository jobStackOfferRepository,
                           JobStackResponseParser jobStackResponseParser) {
        super(url, responseParser);
        this.jobStackOfferRepository = jobStackOfferRepository;
        this.jobStackResponseParser = jobStackResponseParser;
    }

    private List<String> getOfferLinks(String url) throws IOException {
        List<String> offers = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
            String response = httpClient.execute(httpGet, responseHandler);

            Document document = Jsoup.parse(response);
            Elements hrefs = document.select(".jobpost-mainlink");

            for (Element link : hrefs) {
                offers.add(link.attr("href"));
            }

            Elements nextPageLink = document.select("#page_next a");
            if (!nextPageLink.isEmpty()) {
                String nextPageUrl = nextPageLink.get(0).attr("href");
                offers.addAll(getOfferLinks("https://www.jobstack.it" + nextPageUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return offers;
    }

    private String scrapeSingleOffer(String url) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://www.jobstack.it" + url);
            HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
            return httpClient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            System.err.println("An error occurred while scraping the offer: " + e.getMessage());
            return "";
        }
    }

    public List<String> getAllUrls() {
        return jobStackOfferRepository.findAll()
                .stream()
                .map(jobStackOffer -> jobStackOffer.getLink())
                .collect(Collectors.toList());
    }

    public void saveNew(String offerUrl) {
        jobStackOfferRepository.save(new JobStackOffer(offerUrl));
    }

    public List<String> findNewOffers(List<String> offerLinks) {

        List<String> oldOffers = getAllUrls();

        offerLinks.removeAll(oldOffers);

        for (String offer : offerLinks) {
            saveNew(offer);
        }

        return offerLinks;
    }

    @Override
    public List<String> getJobOffers() throws IOException {
        List<String> offerLinks = getOfferLinks(URL);
        List<String> newOffers = findNewOffers(offerLinks);
        List<String> validOffers = new ArrayList<>();
        for (String offerUrl : newOffers) {
            String parsedOffer = scrapeSingleOffer(offerUrl);
            if (jobStackResponseParser.isSuitable(parsedOffer)) {
                String message = jobStackResponseParser.buildMessage(parsedOffer, offerUrl);
                System.out.println(message);
                validOffers.add(message);
            }
        }

        return validOffers;
    }
}