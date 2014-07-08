package ro.freemanfx.productprice.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import ro.freemanfx.productprice.BeanProvider;

public class ProductPriceRepository extends AbstractRepository<ProductPrice> {
    @Override
    public ProductPrice createEntity(Cursor cursor) {
        return null;
    }

    @Override
    public String getTableName() {
        return ProductPrice.TABLE;
    }

    public List<ProductPrice> findByProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = BeanProvider.getReadableDb();
        Cursor cursor = sqLiteDatabase.query(ProductPrice.TABLE, null, ProductPrice.COLUMN_PRODUCT_ID + "=?", new String[]{product.getId().toString()}, null, null, null);
        List<ProductPrice> productPrices = new LinkedList<ProductPrice>();
        while (cursor.moveToNext()) {
            productPrices.add(new ProductPrice(cursor));
        }
        return productPrices;
    }
}
