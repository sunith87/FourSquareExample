package foursquare.sunith.nair.com.foursquareexample.download;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchData implements Parcelable {

    private final String name;
    private final String otherDetails;

    public SearchData(String name, String otherDetails) {
        this.name = name;
        this.otherDetails = otherDetails;
    }

    protected SearchData(Parcel in) {
        name = in.readString();
        otherDetails = in.readString();
    }

    public static final Creator<SearchData> CREATOR = new Creator<SearchData>() {
        @Override
        public SearchData createFromParcel(Parcel in) {
            return new SearchData(in);
        }

        @Override
        public SearchData[] newArray(int size) {
            return new SearchData[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(otherDetails);
    }
}
