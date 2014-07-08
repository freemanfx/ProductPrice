package ro.freemanfx.productprice.domain;

import android.test.AndroidTestCase;

import java.util.List;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.infrastructure.DatabaseHelper;

import static ro.freemanfx.productprice.BeanProvider.placeRepository;
import static ro.freemanfx.productprice.BeanProvider.productRepository;
import static ro.freemanfx.productprice.infrastructure.LocationHelper.parseLocationString;

public class ProductPriceRepositoryTest extends AndroidTestCase {

    public static final String COKE_BARCODE = "125466448789";
    public static final String COKE = "Coke";
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

        ProductPrice productPrice = new ProductPrice(aProduct, aPlace);
        ProductPrice savedProductPrice = productPriceRepository.save(productPrice);

        assertNotNull(savedProductPrice.getId());
        assertEquals(productPriceRepository.count(), 1);
    }

    public void testFindByProduct() {
        Place walmart = placeRepository().save(new Place("WALMART", parseLocationString("10.11|12.11")));
        Place bestBuy = placeRepository().save(new Place("BESTBUY", parseLocationString("12.11|44.55")));

        Product coke = productRepository().save(new Product(COKE, COKE_BARCODE));
        Product rice = productRepository().save(new Product("SPRITE", "1245"));

        ProductPrice cokeAtWalmart = productPriceRepository.save(new ProductPrice(coke, walmart));
        ProductPrice cokeAtBestBuy = productPriceRepository.save(new ProductPrice(coke, bestBuy));
        ProductPrice riceAtBestBuy = productPriceRepository.save(new ProductPrice(rice, bestBuy));

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