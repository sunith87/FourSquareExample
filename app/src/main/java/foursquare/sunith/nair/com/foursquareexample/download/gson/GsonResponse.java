package foursquare.sunith.nair.com.foursquareexample.download.gson;

import com.google.gson.annotations.SerializedName;
import java.util.List;

class GsonResponse {

    @SerializedName("venues")
    List<GsonVenue> gson_venues;

}
