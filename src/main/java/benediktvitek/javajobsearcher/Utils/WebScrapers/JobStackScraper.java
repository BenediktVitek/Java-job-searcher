package benediktvitek.javajobsearcher.Utils.WebScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.JobStackResponseParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;
import java.util.List;

public class JobStackScraper extends HttpClientWebScraper{

    public JobStackScraper(String url, CloseableHttpClient httpClient, JobStackResponseParser responseParser) {
        super(url, httpClient, responseParser);
    }

    @Override
    public List<String> getParsedResponse() throws IOException {
        HttpGet httpGet = new HttpGet(URL);
        HttpClientResponseHandler<String> responseHandler = new BasicHttpClientResponseHandler();
        String response = httpClient.execute(httpGet,responseHandler);
        closeHttpClient();

        return List.of(response);
    }
}