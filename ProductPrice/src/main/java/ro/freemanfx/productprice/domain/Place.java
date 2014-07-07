package ro.freemanfx.productprice.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;

import ro.freemanfx.productprice.infrastructure.LocationHelper;

import static ro.freemanfx.productprice.infrastructure.LocationHelper.parseLocationString;

public class Place extends Entity {
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String COLUMN_LOCATION = "COLUMN_LOCATION";
    public static final String TABLE = "PLACE";

    private final String name;
    private final Location location;

    public Place(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Place(Cursor cursor) {
        super(cursor);
        this.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        this.location = parseLocationString(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
    }

    public static String getCreateTable() {
        return new StringBuilder("CREATE TABLE ").append(TABLE)
                .append(" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMN_NAME).append(" TEXT, ")
                .append(COLUMN_LOCATION).append(" TEXT ")
                .append(" )")
                .toString();
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_LOCATION, LocationHelper.encodeLocation(location));
        return contentValues;
    }
}
