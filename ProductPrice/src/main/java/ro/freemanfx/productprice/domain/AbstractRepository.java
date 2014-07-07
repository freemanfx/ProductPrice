package ro.freemanfx.productprice.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;
import static ro.freemanfx.productprice.BeanProvider.getWritableDb;

public abstract class AbstractRepository {

    public void deleteAll() {
        SQLiteDatabase db = getWritableDb();
        int count = db.delete(getTableName(), null, null);
        Log.d("AbstractRepository", "Deleted " + count + " items");
        db.close();
    }

    public int count() {
        Cursor cursor = getReadableDb().query(getTableName(), null, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    public abstract String getTableName();
}
