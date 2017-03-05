package foursquare.sunith.nair.com.foursquareexample.download.gson;

import com.google.gson.annotations.SerializedName;

class GsonVenue {
    @SerializedName("name")
    String name;

    @SerializedName("contact")
    String contact;

    @SerializedName("location")
    GsonLocation gsonLocation;
}
