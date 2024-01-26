package benediktvitek.javajobsearcher.utils.webscrapers;


import benediktvitek.javajobsearcher.utils.parsers.ResponseParser;

public abstract class HttpClientWebScraper extends WebScraper{

    private final ResponseParser jobstackResponseParser;

    public HttpClientWebScraper(String url,ResponseParser responseParser) {
        super(url);
        this.jobstackResponseParser = responseParser;
    }

}
