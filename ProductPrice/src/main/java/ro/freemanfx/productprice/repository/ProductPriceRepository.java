package ro.freemanfx.productprice.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.domain.ProductPrice;
import ro.freemanfx.productprice.infrastructure.AbstractRepository;

public class ProductPriceRepository extends AbstractRepository<ProductPrice> {
    @Override
    public ProductPrice createEntity(Cursor cursor) {
        return null;
    }

    @Override
    public String getTableName() {
        return ProductPrice.TABLE;
    }

    public List<ProductPrice> findByProductBarcode(String barcode) {
        SQLiteDatabase sqLiteDatabase = BeanProvider.getReadableDb();
        Cursor cursor = sqLiteDatabase.rawQuery(ProductPrice.QUERY_PRODUCT_AND_PLACE_JOIN, new String[]{barcode});
        List<ProductPrice> productPrices = new LinkedList<ProductPrice>();
        while (cursor.moveToNext()) {
            productPrices.add(new ProductPrice(cursor));
        }
        return productPrices;
    }
}
