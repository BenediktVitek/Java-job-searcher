package benediktvitek.javajobsearcher.Utils.Parsers;

import org.springframework.stereotype.Component;

@Component
public class CocumaParser extends ResponseParser{
    @Override
    public String buildMessage(String offer, String url) {
        return null;
    }

    @Override
    public boolean isSuitable(String offer) {
        return false;
    }
}
