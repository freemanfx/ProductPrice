package ro.freemanfx.productprice.domain;

import android.database.Cursor;

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
