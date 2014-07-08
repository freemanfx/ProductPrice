package ro.freemanfx.productprice.domain;

import android.test.AndroidTestCase;

import java.util.List;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.infrastructure.DatabaseHelper;
import ro.freemanfx.productprice.repository.ProductPriceRepository;
import ro.freemanfx.productprice.testfixture.PlaceTestFixture;
import ro.freemanfx.productprice.testfixture.ProductTestFixture;

import static ro.freemanfx.productprice.BeanProvider.placeRepository;
import static ro.freemanfx.productprice.BeanProvider.productRepository;
import static ro.freemanfx.productprice.infrastructure.LocationHelper.parseLocationString;

public class ProductPriceRepositoryTest extends AndroidTestCase {

    public static final String COKE_BARCODE = "125466448789";
    public static final String COKE = "Coke";
    public static final double A_DOLLAR = 1.00D;
    ProductPriceRepository productPriceRepository;

    public void setUp() throws Exception {
        super.setUp();
        getContext().deleteDatabase(DatabaseHelper.DB_NAME);
        BeanProvider.init(getContext());
        productPriceRepository = BeanProvider.productPriceRepository();
    }

    public void testSave() throws Exception {
        Product aProduct = ProductTestFixture.save(ProductTestFixture.aProduct());
        Place aPlace = PlaceTestFixture.save(PlaceTestFixture.aPlace());

        ProductPrice productPrice = new ProductPrice(aProduct, aPlace, A_DOLLAR);
        ProductPrice savedProductPrice = productPriceRepository.save(productPrice);
        assertNotNull(savedProductPrice.getId());
        assertEquals(productPriceRepository.count(), 1);

        List<ProductPrice> byProduct = productPriceRepository.findByProduct(aProduct);
        assertEquals(byProduct.get(0), productPrice);
    }

    public void testFindByProduct() {
        Place walmart = placeRepository().save(new Place("WALMART", parseLocationString("10.11|12.11")));
        Place bestBuy = placeRepository().save(new Place("BESTBUY", parseLocationString("12.11|44.55")));

        Product coke = productRepository().save(new Product(COKE, COKE_BARCODE));
        Product rice = productRepository().save(new Product("SPRITE", "1245"));

        ProductPrice cokeAtWalmart = productPriceRepository.save(new ProductPrice(coke, walmart, 1.05D));
        ProductPrice cokeAtBestBuy = productPriceRepository.save(new ProductPrice(coke, bestBuy, 1.10D));
        ProductPrice riceAtBestBuy = productPriceRepository.save(new ProductPrice(rice, bestBuy, A_DOLLAR));

        List<ProductPrice> cokePrices = productPriceRepository.findByProduct(coke);
        assertEquals(cokePrices.size(), 2);
        assertTrue(cokePrices.contains(cokeAtWalmart));
        assertTrue(cokePrices.contains(cokeAtBestBuy));
    }

    public void tearDown() throws Exception {
        productPriceRepository.deleteAll();

        productRepository().deleteAll();
        placeRepository().deleteAll();
        super.tearDown();
    }
}