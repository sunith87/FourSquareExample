package foursquare.sunith.nair.com.foursquareexample.view;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

/**
 * Created by snair on 05/03/2017.
 */

public class SearchDataListParcelable implements Parcelable {

    private final List<SearchData> dataList;
    private final String queryPlaced;

    public SearchDataListParcelable(List<SearchData> data, String searchMessage) {
        this.dataList = data;
        this.queryPlaced = searchMessage;
    }

    protected SearchDataListParcelable(Parcel in) {
        dataList = in.createTypedArrayList(SearchData.CREATOR);
        queryPlaced = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(dataList);
        dest.writeString(queryPlaced);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchDataListParcelable> CREATOR = new Creator<SearchDataListParcelable>() {
        @Override
        public SearchDataListParcelable createFromParcel(Parcel in) {
            return new SearchDataListParcelable(in);
        }

        @Override
        public SearchDataListParcelable[] newArray(int size) {
            return new SearchDataListParcelable[size];
        }
    };


    public List<SearchData> getDataList() {
        return dataList;
    }

    public String getQueryPlaced() {
        return queryPlaced;
    }
}
