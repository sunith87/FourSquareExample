package foursquare.sunith.nair.com.foursquareexample;

import android.app.Application;

import foursquare.sunith.nair.com.foursquareexample.download.SearchApi;
import foursquare.sunith.nair.com.foursquareexample.download.SearchDownloader;

/**
 * Created by snair on 05/03/2017.
 */

public class FourSqaureApplication extends Application {

    private SearchController mSearchController;
    private SearchApi mSearchApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mSearchController = new SearchController(this);
    }


    public SearchController getSearchController(SearchUIUpdater searchUIUpdater){
        mSearchController.setUIUpdater(searchUIUpdater);
        return mSearchController;
    }

    public SearchApi getSearchApi() {
        return new SearchDownloader();
    }
}
