package ro.freemanfx.productprice.domain;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;

import ro.freemanfx.productprice.infrastructure.Entity;
import ro.freemanfx.productprice.infrastructure.LocationHelper;

import static ro.freemanfx.productprice.infrastructure.LocationHelper.parseLocationString;

public class Place extends Entity {
    public static final String COLUMN_PLACE_NAME = "COLUMN_PLACE_NAME";
    public static final String COLUMN_PLACE_LOCATION = "COLUMN_PLACE_LOCATION";
    public static final String TABLE = "PLACE";

    private final String name;
    private final LatLng location;

    public Place(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }

    public Place(Cursor cursor) {
        super(cursor);
        this.name = cursor.getString(cursor.getColumnIndex(COLUMN_PLACE_NAME));
        this.location = parseLocationString(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE_LOCATION)));
    }

    public static String getCreateTable() {
        return new StringBuilder("CREATE TABLE ").append(TABLE)
                .append(" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMN_PLACE_NAME).append(" TEXT, ")
                .append(COLUMN_PLACE_LOCATION).append(" TEXT ")
                .append(" )")
                .toString();
    }

    public LatLng getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLACE_NAME, name);
        contentValues.put(COLUMN_PLACE_LOCATION, LocationHelper.encodeLocation(location));
        return contentValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (!location.equals(place.location)) return false;
        if (!name.equals(place.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
