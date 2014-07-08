package ro.freemanfx.productprice.domain;

import android.location.Location;
import android.test.AndroidTestCase;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.infrastructure.LocationHelper;

public class PlaceRepositoryTest extends AndroidTestCase {
    private static final String HOME = "Home";
    private static final Location LOCATION = LocationHelper.parseLocationString("12.45665|42.56");

    private PlaceRepository placeRepository;

    public void setUp() throws Exception {
        BeanProvider.init(getContext());
        placeRepository = BeanProvider.placeRepository();
    }

    public void testSave() throws Exception {
        Place place = new Place(HOME, LOCATION);
        placeRepository.save(place);
        assertNotNull(place.getId());
    }

    @Override
    public void tearDown() throws Exception {
        placeRepository.deleteAll();
        super.tearDown();
    }
}