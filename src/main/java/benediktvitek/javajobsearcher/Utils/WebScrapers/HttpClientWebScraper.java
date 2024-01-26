package benediktvitek.javajobsearcher.utils.webscrapers;


import benediktvitek.javajobsearcher.utils.parsers.ResponseParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

public abstract class HttpClientWebScraper extends WebScraper {

    private final ResponseParser jobstackResponseParser;

    public HttpClientWebScraper(String url, ResponseParser responseParser) {
        super(url);
        this.jobstackResponseParser = responseParser;
    }

    protected String scrapePage(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
            return httpClient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            System.err.println("An error occurred while scraping the offer: " + e.getMessage());
            return "";
        }
    }}