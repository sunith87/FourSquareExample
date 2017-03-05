package foursquare.sunith.nair.com.foursquareexample;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

public interface SearchUIUpdater {
    public void handleSuccess(List<SearchData> searchDataList);
    public void handleError(String message);
}
