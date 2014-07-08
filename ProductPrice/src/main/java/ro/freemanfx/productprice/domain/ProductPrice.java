package ro.freemanfx.productprice.domain;

import android.content.ContentValues;
import android.database.Cursor;

import ro.freemanfx.productprice.infrastructure.Entity;

public class ProductPrice extends Entity {
    public static final String COLUMN_PRODUCT_ID = "COLUMN_PRODUCT_ID";
    public static final String COLUMN_PLACE_ID = "COLUMN_PLACE_ID";
    public static final String TABLE = "PRODUCT_PRICE";

    private final Long productId;
    private final Long placeId;

    public ProductPrice(Long productId, Long placeId) {
        this.productId = productId;
        this.placeId = placeId;
    }

    public ProductPrice(Product aProduct, Place aPlace) {
        this(aProduct.getId(), aPlace.getId());
    }

    public ProductPrice(Cursor cursor) {
        super(cursor);
        this.productId = cursor.getLong(cursor.getColumnIndex(COLUMN_PRODUCT_ID));
        this.placeId = cursor.getLong(cursor.getColumnIndex(COLUMN_PLACE_ID));
    }

    public static String getCreateTable() {
        return new StringBuilder("CREATE TABLE ").append(TABLE)
                .append(" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMN_PRODUCT_ID).append(" TEXT, ")
                .append(COLUMN_PLACE_ID).append(" TEXT ")
                .append(" )")
                .toString();
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_ID, productId);
        contentValues.put(COLUMN_PLACE_ID, placeId);
        return contentValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPrice that = (ProductPrice) o;

        if (!placeId.equals(that.placeId)) return false;
        if (!productId.equals(that.productId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + placeId.hashCode();
        return result;
    }
}
