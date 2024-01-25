package benediktvitek.javajobsearcher.utils.parsers;

import java.util.List;

public abstract class ResponseParser {

    public abstract String buildMessage(String offer, String url);

    public abstract boolean isSuitable(String offer);
}
