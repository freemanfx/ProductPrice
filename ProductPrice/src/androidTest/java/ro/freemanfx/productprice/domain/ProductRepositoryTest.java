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
        Product product = new Product(NAME, BARCODE);

        productRepository.save(product);

        Product productFound = productRepository.findByBarcode(BARCODE);
        assertEquals(productFound.getName(), NAME);
        assertEquals(productFound.getBarcode(), BARCODE);
        assertEquals(1, productRepository.count());
    }

    public void testFindByBarCode() throws Exception {
        Product product1 = new Product("Product 1", "101");
        Product product2 = new Product("Product 2", "102");

        productRepository.save(product1);
        productRepository.save(product2);

        Product byBarcode1 = productRepository.findByBarcode(product1.getBarcode());
        assertEquals(byBarcode1.getBarcode(), product1.getBarcode());
    }

    @Override
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        super.tearDown();
    }
}