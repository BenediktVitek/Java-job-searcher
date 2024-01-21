package benediktvitek.javajobsearcher.Utils.WebScrapers;

import benediktvitek.javajobsearcher.Utils.Parsers.ResponseParser;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public abstract class HttpClientWebScraper extends WebScraper{

    protected final ResponseParser responseParser;

    public HttpClientWebScraper(String url,ResponseParser responseParser) {
        super(url);
        this.responseParser = responseParser;
    }

}
