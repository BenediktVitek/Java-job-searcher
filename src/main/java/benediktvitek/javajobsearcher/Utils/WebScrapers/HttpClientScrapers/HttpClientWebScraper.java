package benediktvitek.javajobsearcher.Utils.WebScrapers.HttpClientScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.ResponseParser;
import benediktvitek.javajobsearcher.Utils.WebScrapers.WebScraper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.util.List;

public abstract class HttpClientWebScraper extends WebScraper {

    protected final ResponseParser responseParser;

    public HttpClientWebScraper(String url, ResponseParser responseParser) {
        super(url);
        this.responseParser = responseParser;
    }

    protected abstract List<String> getOfferLinks(String url);

    protected String getPageView(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
            return httpClient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            System.err.println("An error occurred while scraping the offer: " + e.getMessage());
            return "";
        }
    }}