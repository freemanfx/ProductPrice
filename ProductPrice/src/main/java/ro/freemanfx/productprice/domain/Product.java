package ro.freemanfx.productprice.domain;

import android.content.ContentValues;
import android.database.Cursor;

import ro.freemanfx.productprice.infrastructure.Entity;

public class Product extends Entity {
    public static final String TABLE = "product";
    public static final String COLUMN_PRODUCT_NAME = "COLUMN_PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_BARCODE = "COLUMN_PRODUCT_BARCODE";

    private final String name;
    private final String barcode;

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public Product(Cursor cursor) {
        super(cursor);
        this.name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
        this.barcode = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_BARCODE));
    }

    public static String getCreateTable() {
        return new StringBuilder("CREATE TABLE ").append(TABLE)
                .append(" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMN_PRODUCT_NAME).append(" TEXT, ")
                .append(COLUMN_PRODUCT_BARCODE).append(" TEXT ")
                .append(" )")
                .toString();
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, name);
        contentValues.put(COLUMN_PRODUCT_BARCODE, barcode);
        return contentValues;
    }
}
