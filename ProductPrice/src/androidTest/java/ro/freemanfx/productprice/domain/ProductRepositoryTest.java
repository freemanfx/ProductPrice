package ro.freemanfx.productprice.domain;

import android.test.AndroidTestCase;

import ro.freemanfx.productprice.BeanProvider;

public class ProductRepositoryTest extends AndroidTestCase {
    private static final String BARCODE = "123456790123";
    private static final String NAME = "NAME";

    private ProductRepository productRepository;

    public void setUp() throws Exception {
        BeanProvider.init(getContext());
        productRepository = BeanProvider.productRepository();
    }

    public void testSave() throws Exception {
        assertEquals(0, productRepository.count());

        Product product = new Product(NAME, BARCODE);
        long result = productRepository.save(product);
        Product byBarcode = productRepository.findByBarcode(BARCODE);

        assertNotNull(byBarcode);
        assertEquals(1, productRepository.count());
    }

    @Override
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        super.tearDown();
    }
}