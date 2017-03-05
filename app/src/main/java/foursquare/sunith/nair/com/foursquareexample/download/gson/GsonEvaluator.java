package foursquare.sunith.nair.com.foursquareexample.download.gson;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

public class GsonEvaluator {


    public static List<SearchData> evaluateDataFromGson(String data) {
        Gson gson = new Gson();
        GsonSearchData gsonSearchData = gson.fromJson(data, GsonSearchData.class);
        ArrayList<SearchData> searchDataList = new ArrayList<>();
        List<GsonVenue> gsonVenues = gsonSearchData.gsonResponse.gson_venues;

        for (GsonVenue venue : gsonVenues) {
            if (!TextUtils.isEmpty(venue.name)) {
                SearchData searchData = new SearchData(venue.name, tranformOtherDetails(venue));
                searchDataList.add(searchData);
            }
        }
        return searchDataList;
    }

    private static String tranformOtherDetails(GsonVenue venue) {
        GsonLocation gsonLocation = venue.gsonLocation;
        GsonContact contact = venue.contact;

        StringBuilder otherDetails = new StringBuilder();

        if (contact != null && contact.formattedPhone != null) {
            otherDetails.append("Phone: ");
            otherDetails.append(contact.formattedPhone);
            otherDetails.append("\n");
        }

        if (gsonLocation != null) {
            List<String> formattedAddress = gsonLocation.formattedAddress;
            if (formattedAddress != null) {
                for (String address : formattedAddress) {
                    if (!TextUtils.isEmpty(address)) {
                        otherDetails.append(address);
                        otherDetails.append("\n");
                    }
                }
                return otherDetails.toString();
            }
        }

        return null;
    }
}
