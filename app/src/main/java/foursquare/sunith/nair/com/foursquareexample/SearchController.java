package foursquare.sunith.nair.com.foursquareexample;

import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;

import foursquare.sunith.nair.com.foursquareexample.download.LatLong;
import foursquare.sunith.nair.com.foursquareexample.download.SearchApi;
import foursquare.sunith.nair.com.foursquareexample.geolocation.GeoLocationAsyncTask;
import foursquare.sunith.nair.com.foursquareexample.geolocation.GeoLocationResult;

public class SearchController implements SearchView.OnQueryTextListener {
    private final FourSqaureApplication mFourSqaureApplication;
    private SearchUIUpdater mSearchUIUpdater;
    private final SearchApi mSearchApi;
    private String queryPlaced;

    public SearchController(FourSqaureApplication fourSqaureApplication) {
        mFourSqaureApplication = fourSqaureApplication;
        mSearchApi = fourSqaureApplication.getSearchApi();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchUIUpdater.searchStarted(query);
        findPlace(query);
        queryPlaced = query;
        return true;
    }

    private void findPlace(String query) {
        GeoLocationAsyncTask asyncTask = new GeoLocationAsyncTask(mFourSqaureApplication, getGeoLocationListener());
        asyncTask.execute(query);
    }

    @NonNull
    private GeoLocationAsyncTask.Listener getGeoLocationListener() {
        return new GeoLocationAsyncTask.Listener() {
            @Override
            public void update(GeoLocationResult result) {
                handleGeoLocationResult(result);
            }
        };
    }

    private void handleGeoLocationResult(GeoLocationResult result) {
        if (result.isSuccessful()) {
            doApiSearch(result.getAddress());
        } else {
            handleError(result);
        }
    }


    private void doApiSearch(Address address) {
        LatLong latLong = LatLong.from(address);
        mSearchApi.searchVenues(latLong, getSearchApiListener());

    }

    @NonNull
    private SearchApi.SearchVenuesListener getSearchApiListener() {
        return new SearchApi.SearchVenuesListener() {
            @Override
            public void onResult(SearchResult searchResult) {
                if (searchResult.isSuccessful()) {
                    handleSuccess(searchResult);
                } else {
                    handleError(searchResult);
                }
            }
        };
    }

    private void handleSuccess(SearchResult searchResult) {
        mSearchUIUpdater.handleSuccess(searchResult.getSearchData(),queryPlaced);
    }

    private void handleError(ErrorMessage errorMessage) {
        mSearchUIUpdater.handleError(errorMessage.getErrorMessage());
    }

    public void setUIUpdater(SearchUIUpdater searchUIUpdater) {
        mSearchUIUpdater = searchUIUpdater;
    }
}
