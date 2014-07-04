package ro.freemanfx.productprice.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.test.AndroidTestCase;

import ro.freemanfx.productprice.domain.model.Product;
import ro.freemanfx.productprice.domain.provider.ProductPriceProvider;

public class ProductPriceProviderTest extends AndroidTestCase {

    private static final String BARCODE = "01234";
    private static final String COKE = "Coke";

    public void testProduct() throws Exception {
        Product product = new Product();
        product.setBarcode(BARCODE);
        product.setName(COKE);

        contentResolver().insert(ProductPriceProvider.PRODUCT_CONTENT_URI, product.getContentValues());

        Cursor cursor = contentResolver().query(ProductPriceProvider.PRODUCT_CONTENT_URI, null, null, null, null);

        assertEquals(cursor.getCount(), 1);
        cursor.moveToFirst();
        Product retrievedProduct = new Product(cursor);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        contentResolver().delete(ProductPriceProvider.PRODUCT_CONTENT_URI, null, null);
    }

    private ContentResolver contentResolver() {
        return getContext().getContentResolver();
    }
}
