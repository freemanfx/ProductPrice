package ro.freemanfx.productprice.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;
import static ro.freemanfx.productprice.BeanProvider.getWritableDb;

public class ProductRepository extends AbstractRepository {

    public long save(Product product) {
        SQLiteDatabase writableDb = getWritableDb();
        try {
            return writableDb.insert(getTableName(), null, product.getContentValues());
        } finally {
            writableDb.close();
        }

    }

    public Product findByBarcode(String barcode) {
        Cursor cursor = getReadableDb().query(getTableName(), null, Product.COLUMN_PRODUCT_BARCODE + "=?", new String[]{barcode}, null, null, null);
        cursor.moveToFirst();
        return new Product(cursor);
    }

    @Override
    public String getTableName() {
        return Product.TABLE;
    }
}
