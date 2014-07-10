package ro.freemanfx.productprice.testfixture;

import com.google.android.gms.maps.model.LatLng;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.infrastructure.LocationHelper;

public class PlaceTestFixture extends TestFixture {
    private static final LatLng TEST_LOCATION = LocationHelper.newLocation(12.33445544, 12.221133);

    public static Place aPlace() {
        return new Place(TEST_NAME, TEST_LOCATION);
    }

    public static Place save(Place place) {
        return BeanProvider.placeRepository().save(place);
    }
}
