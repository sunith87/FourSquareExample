package foursquare.sunith.nair.com.foursquareexample.download;

import foursquare.sunith.nair.com.foursquareexample.SearchResult;

public interface SearchApi {

    public void searchVenues(LatLong latLong, SearchVenuesListener listener);

    interface SearchVenuesListener {
        void onResult(SearchResult searchResult);
    }
}
