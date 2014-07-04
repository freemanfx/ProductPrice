package ro.freemanfx.productprice.domain;

import android.database.Cursor;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;
import static ro.freemanfx.productprice.BeanProvider.getWritableDb;

public abstract class AbstractRepository {

    public void deleteAll() {
        getWritableDb().beginTransaction();
        getWritableDb().delete(getTableName(), null, null);
        getWritableDb().endTransaction();
    }

    public int count() {
        Cursor cursor = getReadableDb().query(getTableName(), null, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    public abstract String getTableName();
}
