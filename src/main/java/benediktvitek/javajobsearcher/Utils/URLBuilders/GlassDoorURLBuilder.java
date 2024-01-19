package benediktvitek.javajobsearcher.Utils.URLBuilders;

import benediktvitek.javajobsearcher.Utils.Country;

public class GlassDoorURLBuilder extends URLBuilder {

    private final String base = "https://www.glassdoor.com/Job/";

    public GlassDoorURLBuilder(String position, Country country, int jobOfferAge) {
        super(position, country, jobOfferAge);
    }

    @Override
    public String getURL() {
        URL = base;
        setCountry();
        setPosition();
        setJobOfferAge();
        return URL;
    }

    private void setCountry() {
        if (country == Country.CZECH_REPUBLIC) {
            URL = URL.concat("czech-republic-");
        }
    }

    private void setPosition() {
        String[] words = position.split(" ");
        for (String word : words) {
            URL = URL.concat(word).concat("-");
        }
        URL = URL.concat("jobs-SRCH_IL.0,14_IN77_KO15,36.htm?"); //Todo: figure out algorithm used for queries on Glass Doors
    }

    private void setJobOfferAge() {
        URL = URL.concat("fromAge=").concat(String.valueOf(jobOfferAge));
    }
}
