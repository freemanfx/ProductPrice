package ro.freemanfx.productprice.testfixture;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.domain.Product;

public class ProductTestFixture extends TestFixture {
    public static final String TEST_BARCODE = "01234567890123";

    public static Product aProduct() {
        return new Product(TEST_NAME, TEST_BARCODE);
    }

    public static Product save(Product product) {
        return BeanProvider.productRepository().save(product);
    }
}
