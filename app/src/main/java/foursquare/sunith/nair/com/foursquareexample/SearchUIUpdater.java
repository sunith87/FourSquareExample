package foursquare.sunith.nair.com.foursquareexample;

import android.support.v7.widget.SearchView;
import android.view.View;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

public interface SearchUIUpdater {
    public void handleSuccess(List<SearchData> searchDataList);
    public void handleError(String message);
    public SearchView getSearchView();
}
