package ro.freemanfx.productprice.domain;

import android.test.AndroidTestCase;

import com.google.android.gms.maps.model.LatLng;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.infrastructure.LocationHelper;
import ro.freemanfx.productprice.repository.PlaceRepository;

public class PlaceRepositoryTest extends AndroidTestCase {
    private static final String HOME = "Home";
    private static final LatLng TEST_LOCATION = LocationHelper.newLocation(12.45665, 42.56);

    private PlaceRepository placeRepository;

    public void setUp() throws Exception {
        BeanProvider.init(getContext());
        placeRepository = BeanProvider.placeRepository();
    }

    public void testSave() throws Exception {
        Place place = new Place(HOME, TEST_LOCATION);
        placeRepository.save(place);
        assertNotNull(place.getId());
    }

    @Override
    public void tearDown() throws Exception {
        placeRepository.deleteAll();
        super.tearDown();
    }
}