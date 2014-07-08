package ro.freemanfx.productprice.domain;

import ro.freemanfx.productprice.BeanProvider;

public class ProductTestFixture extends TestFixture {
    public static final String TEST_BARCODE = "01234567890123";

    public static Product aProduct() {
        return new Product(TEST_NAME, TEST_BARCODE);
    }

    public static Product save(Product product) {
        return BeanProvider.productRepository().save(product);
    }
}
