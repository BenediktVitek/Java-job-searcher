package benediktvitek.javajobsearcher.Utils.Parsers;

import java.util.List;

public abstract class ResponseParser {

    public abstract List<String> getJobDetails(String response);
}
