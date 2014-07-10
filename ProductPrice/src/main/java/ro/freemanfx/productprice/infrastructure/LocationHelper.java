package ro.freemanfx.productprice.infrastructure;

import com.google.android.gms.maps.model.LatLng;

public class LocationHelper {
    private static final String SPLIT_CHAR = "-";

    public static LatLng parseLocationString(String locationString) {
        String[] split = locationString.split(SPLIT_CHAR);
        return newLocation(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
    }

    public static String encodeLocation(LatLng location) {
        return String.valueOf(location.latitude) + SPLIT_CHAR + String.valueOf(location.longitude);
    }

    public static LatLng newLocation(double latitude, double longitude) {
        return new LatLng(latitude, longitude);
    }
}
