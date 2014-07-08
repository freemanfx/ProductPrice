package ro.freemanfx.productprice.repository;

import android.database.Cursor;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.infrastructure.AbstractRepository;

public class PlaceRepository extends AbstractRepository<Place> {
    @Override
    public Place createEntity(Cursor cursor) {
        return new Place(cursor);
    }

    @Override
    public String getTableName() {
        return Place.TABLE;
    }
}
