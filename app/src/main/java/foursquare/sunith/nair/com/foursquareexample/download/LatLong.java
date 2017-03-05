package foursquare.sunith.nair.com.foursquareexample.download;

import android.location.Address;

public class LatLong {


    private final double latitude;
    private final double longitude;

    private LatLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LatLong from(Address address) {
        return new LatLong(address.getLatitude(), address.getLongitude());
    }


    public String getLatLongQueryString() {
        return latitude + ", " + longitude;
    }
}
