package ro.freemanfx.productprice.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;
import static ro.freemanfx.productprice.BeanProvider.getWritableDb;

public class ProductRepository extends AbstractRepository {

    public void save(Product product) {
        SQLiteDatabase writableDb = getWritableDb();
        writableDb.insert(getTableName(), null, product.getContentValues());
        writableDb.close();
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
