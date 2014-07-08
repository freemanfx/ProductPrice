package ro.freemanfx.productprice.domain;

import android.test.AndroidTestCase;

import java.util.List;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.repository.ProductRepository;

public class ProductRepositoryTest extends AndroidTestCase {
    private static final String BARCODE = "123456790123";
    private static final String NAME = "NAME";
    private static final String BARCODE1 = "101";
    private static final String BARCODE2 = "102";

    private ProductRepository productRepository;

    public void setUp() throws Exception {
        BeanProvider.init(getContext());
        productRepository = BeanProvider.productRepository();
    }

    public void testSave() throws Exception {
        Product product = new Product(NAME, BARCODE);

        productRepository.save(product);
        assertNotNull(product.getId());

        Product productFound = productRepository.findByBarcode(BARCODE);
        assertNotNull(productFound.getId());
        assertEquals(productFound.getName(), NAME);
        assertEquals(productFound.getBarcode(), BARCODE);
    }

    public void testCount() {
        Product product1 = new Product("Product 1", BARCODE1);
        Product product2 = new Product("Product 2", BARCODE2);
        productRepository.save(product1);
        productRepository.save(product2);

        assertEquals(2, productRepository.count());
    }

    public void testFindByBarCode() throws Exception {
        Product product1 = new Product("Product 1", BARCODE1);
        Product product2 = new Product("Product 2", BARCODE2);
        productRepository.save(product1);
        productRepository.save(product2);

        Product byBarcode1 = productRepository.findByBarcode(product1.getBarcode());
        assertEquals(byBarcode1.getBarcode(), product1.getBarcode());
    }

    public void testFindAll() throws Exception {
        Product product1 = new Product("Product 1", BARCODE1);
        Product product2 = new Product("Product 2", BARCODE2);
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());
    }

    @Override
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        super.tearDown();
    }
}