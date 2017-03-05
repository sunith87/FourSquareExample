package foursquare.sunith.nair.com.foursquareexample.download;

import android.support.annotation.NonNull;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.SearchResult;

public class SearchDownloader implements SearchApi {

    private SearchVenuesListener listener;

    @Override
    public void searchVenues(LatLong latLong, SearchVenuesListener listener) {
        VenuesDownload venuesDownload = new VenuesDownload(getVenuesListener());
        venuesDownload.execute(latLong);
        this.listener = listener;
    }

    @NonNull
    private VenuesDownload.VenuesListner getVenuesListener() {
        return new VenuesDownload.VenuesListner() {
            @Override
            public void onResult(VenuesSearchResult result) {
                SearchResult searchResult;
                if (result.isSuccessful()) {
                    searchResult = new SearchResult(getSearchDataList(result), null);
                } else {
                    searchResult = new SearchResult(null, getMessage(result));
                }
                listener.onResult(searchResult);
            }
        };
    }

    private List<SearchData> getSearchDataList(VenuesSearchResult result) {
        return result.getSearchData();
    }

    private String getMessage(VenuesSearchResult result) {
        Throwable throwable = result.getThrowable();
        String message = "Downloading error: ";

        if (throwable != null) {
            message = message + throwable;
        } else {
            message = message + "Http Error code - " + result.getHttpCode();
        }

        return message;
    }
}
