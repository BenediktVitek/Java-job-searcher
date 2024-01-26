package benediktvitek.javajobsearcher.utils.parsers;

import org.springframework.stereotype.Component;

@Component
public abstract class ResponseParser {

    public abstract String buildMessage(String offer, String url);

    public abstract boolean isSuitable(String offer);
}
