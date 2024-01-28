package benediktvitek.javajobsearcher.Utils.Parsers;

import org.springframework.stereotype.Component;

@Component
public class IndeedResponseParser extends ResponseParser {
    @Override
    public String buildMessage(String offer, String url) {
        return offer.concat("\n" + url);
    }

    @Override
    public boolean isSuitable(String offer) {
        return offer.matches("(?s).*\\b[Jj]ava\\b.*\\b[Jj]unior\\b.*|.*\\b[Jj]unior\\b.*\\b[Jj]ava\\b.*");
    }
}
