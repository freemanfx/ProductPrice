package ro.freemanfx.productprice.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.orman.dbms.exception.QueryExecutionException;
import org.orman.mapper.MappingSession;
import org.orman.mapper.ModelQuery;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.domain.Product;

public class ProductRepositoryTest extends AndroidTestCase {

    private static final String BARCODE = "01234567890";
    private static final String COKE = "Coke";
    private ProductRepository productRepository;


    public void setUp() throws Exception {
        super.setUp();
        BeanProvider.init(getContext());
        productRepository = BeanProvider.productRepository();
        if (!MappingSession.isSessionStarted()) {
            MappingSession.registerEntity(Product.class);
            MappingSession.start();
        }
    }

    public void testSave() {
        Product aProduct = new Product(BARCODE, COKE);

        productRepository.save(aProduct);

        Product byBarCode = productRepository.findByBarCode(BARCODE);
        assertEquals(byBarCode.getBarcode(), BARCODE);
        assertEquals(byBarCode.getName(), COKE);
    }

    public void testBarcodeIsPrimaryKey() throws Exception {
        Product aProduct = new Product(BARCODE, COKE);
        Product anotherProduct = new Product(BARCODE, COKE);

        try {
            productRepository.save(aProduct);
            productRepository.save(anotherProduct);
            Assert.fail("Should throw exception !");
        } catch (QueryExecutionException e) {
            //Success
        }
    }

    public void tearDown() throws Exception {
        Product.execute(ModelQuery.delete().from(Product.class).getQuery());
        super.tearDown();
    }
}