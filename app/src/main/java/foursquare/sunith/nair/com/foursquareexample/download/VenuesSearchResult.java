package foursquare.sunith.nair.com.foursquareexample.download;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.gson.GsonEvaluator;

public class VenuesSearchResult {

    public final static int VENUES_DEFAULT_ERROR_EXCEPTION = -1000;

    private final int httpCode;
    private final boolean isSuccessful;
    private final String data;
    private final Throwable throwable;


    private VenuesSearchResult(boolean isSuccessful, int httpCode, String data, Throwable throwable) {
        this.isSuccessful = isSuccessful;
        this.httpCode = httpCode;
        this.data = data;
        this.throwable = throwable;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getRawData() {
        return data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public List<SearchData> getSearchData() {
        return GsonEvaluator.evaluateDataFromGson(data);
    }

    public static class Builder {
        private final int httpCode;
        private final boolean isSuccessful;
        private String data;
        private Throwable throwable;

        public Builder(boolean isSuccessful, int httpCode) {
            this.isSuccessful = isSuccessful;
            this.httpCode = httpCode;
        }

        public Builder withData(String data) {
            this.data = data;
            return this;
        }

        public Builder withException(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        public VenuesSearchResult build() {
            return new VenuesSearchResult(isSuccessful, httpCode, data, throwable);
        }

    }

}
