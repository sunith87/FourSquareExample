package foursquare.sunith.nair.com.foursquareexample.download;

public class SearchData {

    private final String name;
    private final String otherDetails;

    public SearchData(String name, String otherDetails) {

        this.name = name;
        this.otherDetails = otherDetails;
    }

    public String getName() {
        return name;
    }

    public String getOtherDetails() {
        return otherDetails;
    }
}
