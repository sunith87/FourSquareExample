package foursquare.sunith.nair.com.foursquareexample.geolocation;

import android.location.Address;
import foursquare.sunith.nair.com.foursquareexample.ErrorMessage;

public class GeoLocationResult implements ErrorMessage {
    private final Address address;
    private final Throwable exception;

    public GeoLocationResult(Address address) {
        this.address = address;
        this.exception = null;
    }

    public GeoLocationResult(Throwable exception) {
        this.address = null;
        this.exception = exception;
    }

    public boolean isSuccessful() {
        return address != null;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String getErrorMessage() {
        String message = null;
        if (exception != null) {
            message = exception.getMessage();
        } else {
            message = DEFAULT_ERROR;
        }
        return message;
    }
}
