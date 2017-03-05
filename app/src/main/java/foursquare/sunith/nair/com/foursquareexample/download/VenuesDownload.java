package foursquare.sunith.nair.com.foursquareexample.download;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

class VenuesDownload extends AsyncTask<LatLong, String, VenuesSearchResult> {

    private static final String NEAR_KEY = "near";
    private static final String DATE_KEY = "v";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final String CLIENT_ID_KEY = "client_id";
    private static final String CLIENT_ID = "3IJOMBV2KYDPF5PAFXX2XPSGZ4KUO5VYGKPVOMECTLACP3TJ";
    private static final String CLIENT_SECRET_KEY = "client_secret";
    private static final String CLIENT_SECRET = "XACYXYWYCSBU5FDZ0RCDJUDULTNQUVXHPZCDIPWOLFJ5QQUU";
    private static final String BASE_URL = "https://api.foursquare.com/v2/venues/search";
    private final VenuesListner listener;


    public interface VenuesListner{
        void onResult(VenuesSearchResult result);
    }

    public VenuesDownload (VenuesListner listener){
        this.listener = listener;
    }

    @Override
    protected VenuesSearchResult doInBackground(LatLong... latLong) {

        VenuesSearchResult result;

        LatLong latLongVal = latLong[0];
        String latLongQueryString = latLongVal.getLatLongQueryString();

        try {
            URL url = new URL(getUrlWithQueries(latLongQueryString));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String data = getData(urlConnection);
                result = new VenuesSearchResult.Builder(true, responseCode).withData(data).build();
            } else {
                result = new VenuesSearchResult.Builder(false, responseCode).build();
            }
        } catch (MalformedURLException e) {
            result = new VenuesSearchResult.Builder(false, VenuesSearchResult.VENUES_DEFAULT_ERROR_EXCEPTION).withException(e).build();
            e.printStackTrace();
        } catch (IOException e) {
            result = new VenuesSearchResult.Builder(false, VenuesSearchResult.VENUES_DEFAULT_ERROR_EXCEPTION).withException(e).build();
            e.printStackTrace();
        }

        return result;
    }


    @Override
    protected void onPostExecute(VenuesSearchResult venuesSearchResult) {
        super.onPostExecute(venuesSearchResult);
        listener.onResult(venuesSearchResult);
    }

    private String getData(HttpURLConnection urlConnection) throws IOException {

        String data = null;
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String read;
        StringBuilder builder = new StringBuilder();
        while ((read = reader.readLine()) != null) {
            builder.append(read);
        }

        reader.close();
        data = builder.toString();
        return data;
    }

    private String getUrlWithQueries(String latLongQueryString) {

        HashMap<String, String> allQueries = getAllQueries(latLongQueryString);
        Uri.Builder builder = new Uri.Builder();
        builder.encodedPath(BASE_URL);
        for (String key : allQueries.keySet()) {
            builder.appendQueryParameter(key, allQueries.get(key));
        }

        return builder.build().toString();
    }

    private HashMap<String, String> getAllQueries(String latLongQueryString) {
        HashMap<String, String> queries = new HashMap<>();
        queries.put(NEAR_KEY, latLongQueryString);
        queries.put(DATE_KEY, getDate());
        queries.put(CLIENT_ID_KEY, CLIENT_ID);
        queries.put(CLIENT_SECRET_KEY, CLIENT_SECRET);

        return queries;
    }

    private String getDate() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());

        return DATE_FORMAT.format(instance.getTime());
    }
}
