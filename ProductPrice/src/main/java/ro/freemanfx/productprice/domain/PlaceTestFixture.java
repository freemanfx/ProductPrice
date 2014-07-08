package ro.freemanfx.productprice.domain;

import android.location.Location;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.infrastructure.LocationHelper;

public class PlaceTestFixture extends TestFixture {
    private static final Location TEST_LOCATION = LocationHelper.parseLocationString("12.334455|44.221133");

    public static Place aPlace() {
        return new Place(TEST_NAME, TEST_LOCATION);
    }

    public static Place save(Place place) {
        return BeanProvider.placeRepository().save(place);
    }
}
