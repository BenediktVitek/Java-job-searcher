package benediktvitek.javajobsearcher.utils.urlbuilders;


import benediktvitek.javajobsearcher.utils.enums.Country;

public abstract class URLBuilder {

    protected  String URL;
    protected String position;
    protected Country country;
    protected int jobOfferAge;

    public URLBuilder(String position, Country country, int jobOfferAge) {
        this.position = position;
        this.country = country;
        this.jobOfferAge = jobOfferAge;
    }

    public abstract String getURL();
}
