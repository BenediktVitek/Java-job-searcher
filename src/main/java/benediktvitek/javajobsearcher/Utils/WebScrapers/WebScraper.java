package benediktvitek.javajobsearcher.Utils.WebScrapers;

import java.io.IOException;
import java.util.List;

public abstract class WebScraper {

    protected final String URL;

    public String getURL() {
        return URL;
    }

    public WebScraper(String url) {
        URL = url;
    }

    public abstract List<String> getJobOffers() throws IOException;
}
