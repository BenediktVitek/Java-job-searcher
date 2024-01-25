package benediktvitek.javajobsearcher.utils.webscrapers;

import benediktvitek.javajobsearcher.utils.parsers.JobStackResponseParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JobStackScraper extends HttpClientWebScraper {

    private static final JobStackResponseParser jobStackResponseParser = new JobStackResponseParser();

    public JobStackScraper(String url) {
        super(url, jobStackResponseParser);
    }

    public List<String> getParsedResponseTest(String url) throws IOException {
        List<String> offers = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
            String response = httpClient.execute(httpGet, responseHandler);

            Document document = Jsoup.parse(response);
            Elements hrefs = document.select(".jobpost-mainlink");

            for (Element link : hrefs) {
                offers.add(link.attr("href"));
                System.out.println(link.attr("href"));
            }

            Elements nextPageLink = document.select("#page_next a");
            if (!nextPageLink.isEmpty()) {
                String nextPageUrl = nextPageLink.get(0).attr("href");
                offers.addAll(getParsedResponseTest("https://www.jobstack.it" + nextPageUrl));

                System.out.println(nextPageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return offers;
    }

    @Override
    public List<String> getJobOffers() throws IOException {
        return getParsedResponseTest(URL);
    }
}