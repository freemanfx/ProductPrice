package ro.freemanfx.productprice.domain;

import android.content.ContentValues;
import android.database.Cursor;

import ro.freemanfx.productprice.infrastructure.Entity;

public class ProductPrice extends Entity {
    public static final String TABLE = "PRODUCT_PRICE";
    public static final String COLUMN_PRODUCT_ID = "COLUMN_PRODUCT_ID";
    public static final String COLUMN_PLACE_ID = "COLUMN_PLACE_ID";
    public static final String COLUMN_PRICE = "COLUMN_PRICE";

    public static final String QUERY_PRODUCT_AND_PLACE_JOIN = "SELECT * FROM " + TABLE + " PP"
            + " INNER JOIN " + Product.TABLE + " PR ON PP." + COLUMN_PRODUCT_ID + "=" + " PR." + COLUMN_ID
            + " INNER JOIN " + Place.TABLE + " PL ON PP." + COLUMN_PLACE_ID + "=" + " PL." + COLUMN_ID
            + " WHERE PR." + Product.COLUMN_PRODUCT_BARCODE + "=? ORDER BY " + COLUMN_PRICE + " ASC";
    private final Product product;
    private final Place place;
    private final Double price;

    public ProductPrice(Product product, Place place, Double price) {
        this.product = product;
        this.place = place;
        this.price = price;
    }

    public ProductPrice(Cursor cursor) {
        super(cursor);
        this.product = productFromCursor(cursor);
        this.place = placeFromCursor(cursor);
        this.price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
    }

    public static String getCreateTable() {
        return new StringBuilder("CREATE TABLE ").append(TABLE)
                .append(" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COLUMN_PRODUCT_ID).append(" TEXT, ")
                .append(COLUMN_PLACE_ID).append(" TEXT, ")
                .append(COLUMN_PRICE).append(" REAL ")
                .append(" )")
                .toString();
    }

    private Place placeFromCursor(Cursor cursor) {
        return new Place(cursor);
    }

    private Product productFromCursor(Cursor cursor) {
        return new Product(cursor);
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_ID, product.getId());
        contentValues.put(COLUMN_PLACE_ID, place.getId());
        contentValues.put(COLUMN_PRICE, price);
        return contentValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPrice that = (ProductPrice) o;

        if (!place.equals(that.place)) return false;
        if (!price.equals(that.price)) return false;
        if (!product.equals(that.product)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + place.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    public Double getPrice() {
        return price;
    }

    public Place getPlace() {
        return place;
    }

    public Product getProduct() {
        return product;
    }
}
