package ro.freemanfx.productprice.domain;

import android.location.Location;

public class Place {
    private Long id;
    private final String name;
    private final String address;
    private final Location location;

    public Place(String name, String address, Location location){
        this.name = name;
        this.address = address;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Location getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }
}
