package ro.freemanfx.productprice;

import ro.freemanfx.productprice.domain.Place;

public class AppContext {
    private static Place place;

    public static Place getPlace() {
        return place;
    }

    public static void setPlace(Place place) {
        AppContext.place = place;
    }
}
