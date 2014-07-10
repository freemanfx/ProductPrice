package ro.freemanfx.productprice.repository;

import android.database.Cursor;

import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.infrastructure.AbstractRepository;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;

public class ProductRepository extends AbstractRepository<Product> {

    public Product findByBarcode(String barcode) {
        Cursor cursor = getReadableDb().query(getTableName(), null, Product.COLUMN_PRODUCT_BARCODE + "=?", new String[]{barcode}, null, null, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
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
