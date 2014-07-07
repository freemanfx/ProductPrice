package ro.freemanfx.productprice.domain;

import android.database.Cursor;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;

public class ProductRepository extends AbstractRepository<Product> {

    public Product findByBarcode(String barcode) {
        Cursor cursor = getReadableDb().query(getTableName(), null, Product.COLUMN_PRODUCT_BARCODE + "=?", new String[]{barcode}, null, null, null);
        cursor.moveToFirst();
        return new Product(cursor);
    }

    @Override
    public Product createEntity(Cursor cursor) {
        return new Product(cursor);
    }

    @Override
    public String getTableName() {
        return Product.TABLE;
    }
}
