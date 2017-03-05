package foursquare.sunith.nair.com.foursquareexample.geolocation;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.FourSqaureApplication;


public class GeoLocationAsyncTask extends AsyncTask<String, String, GeoLocationResult> {

    public static final String NULL_LATITUDE_OR_LONGITUDE = "Address has null latitude or longitude";
    public static final String GEOCODER_NOT_PRESENT = "Geocoder not present";
    private final FourSqaureApplication mFourSqaureApplication;
    private final Listener mGeoLocationListener;

    public interface Listener {
        public void update(GeoLocationResult result);
    }

    public GeoLocationAsyncTask(FourSqaureApplication application, Listener listener) {
        mFourSqaureApplication = application;
        mGeoLocationListener = listener;
    }

    @Override
    protected GeoLocationResult doInBackground(String... strings) {

        GeoLocationResult result = null;

        if (Geocoder.isPresent()) {

            Geocoder geocoder = new Geocoder(mFourSqaureApplication);
            try {

                String query = strings[0];
                List<Address> fromLocationName = geocoder.getFromLocationName(query, 1);
                Address address = fromLocationName.get(0);
                if (address.hasLatitude() && address.hasLongitude()) {
                    result = new GeoLocationResult(address);
                } else {
                    result = new GeoLocationResult(new NullPointerException(NULL_LATITUDE_OR_LONGITUDE));
                }

            } catch (IOException e) {
                result = new GeoLocationResult(e);
                e.printStackTrace();
            }
        } else {
            result = new GeoLocationResult(new RuntimeException(GEOCODER_NOT_PRESENT));
        }
        return result;
    }

    @Override
    protected void onPostExecute(GeoLocationResult geoLocationResult) {
        super.onPostExecute(geoLocationResult);
        mGeoLocationListener.update(geoLocationResult);

    }


}
