package ro.freemanfx.productprice.infrastructure;

import android.location.Location;

public class LocationHelper {
    private static final String MOCK_PROVIDER = "flp";
    private static final String SPLIT_CHAR = "\\|";

    public static Location parseLocationString(String locationString) {
        String[] split = locationString.split(SPLIT_CHAR);
        Location location = new Location(MOCK_PROVIDER);
        location.setLatitude(Double.parseDouble(split[0]));
        location.setLongitude(Double.parseDouble(split[1]));
        return location;
    }

    public static String encodeLocation(Location location) {
        return String.valueOf(location.getLatitude()) + SPLIT_CHAR + String.valueOf(location.getLongitude());
    }
}
