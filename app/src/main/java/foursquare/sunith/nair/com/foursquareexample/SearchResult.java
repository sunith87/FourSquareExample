package foursquare.sunith.nair.com.foursquareexample;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

public class SearchResult implements ErrorMessage {

    private final List<SearchData>  searchDataList;
    private final String errorMessage;

    public SearchResult(List<SearchData>  data, String errorMessage) {
        this.searchDataList = data;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        String error;
        if (errorMessage != null) {
            error = errorMessage;
        } else {
            error = DEFAULT_ERROR;
        }
        return error;
    }

    public boolean isSuccessful() {
        return searchDataList != null && !searchDataList.isEmpty();
    }


    public List<SearchData> getSearchData() {
        return searchDataList;
    }
}
