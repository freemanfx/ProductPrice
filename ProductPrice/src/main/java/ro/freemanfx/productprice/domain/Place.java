package ro.freemanfx.productprice.domain;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    private final String name;
    private final LatLng location;

    public Place(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (!location.equals(place.location)) return false;
        if (!name.equals(place.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
