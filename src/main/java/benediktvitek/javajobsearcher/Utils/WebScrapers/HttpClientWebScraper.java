package benediktvitek.javajobsearcher.utils.webscrapers;


import benediktvitek.javajobsearcher.utils.parsers.ResponseParser;

public abstract class HttpClientWebScraper extends WebScraper{

    protected final ResponseParser responseParser;

    public HttpClientWebScraper(String url,ResponseParser responseParser) {
        super(url);
        this.responseParser = responseParser;
    }

}
