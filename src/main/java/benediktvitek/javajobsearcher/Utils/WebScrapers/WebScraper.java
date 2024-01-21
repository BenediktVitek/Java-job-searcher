package benediktvitek.javajobsearcher.utils.webscrapers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public abstract class WebScraper {

    protected final String URL;

    public WebScraper(String url) {
        URL = url;
    }

    public abstract List<String> getParsedResponse() throws IOException;
}
