package ro.freemanfx.productprice.repository;

import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;
import org.orman.mapper.MappingSession;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.domain.Product;

public class ProductRepositoryTest extends AndroidTestCase {

    private static final String BARCODE = "01234567890";
    private static final String COKE = "Coke";
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        BeanProvider.init(getContext());

        productRepository = BeanProvider.productRepository();
        MappingSession.registerEntity(Product.class);
        MappingSession.start();
    }

    @Test
    public void testSave() {
        Product aProduct = new Product(BARCODE, COKE);

        productRepository.save(aProduct);

        Product byBarCode = productRepository.findByBarCode(BARCODE);
        assertEquals(byBarCode.getBarcode(), BARCODE);
        assertEquals(byBarCode.getName(),COKE);
    }
}