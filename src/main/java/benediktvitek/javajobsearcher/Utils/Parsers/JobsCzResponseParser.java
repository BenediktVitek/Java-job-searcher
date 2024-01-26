package benediktvitek.javajobsearcher.utils.parsers;

import org.springframework.stereotype.Component;

@Component
public class JobsCzResponseParser extends ResponseParser{



    @Override
    public String buildMessage(String offer, String url) {
        return null;
    }

    @Override
    public boolean isSuitable(String offer) {
        return false;
    }
}
