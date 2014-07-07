package ro.freemanfx.productprice.domain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import static ro.freemanfx.productprice.BeanProvider.getReadableDb;
import static ro.freemanfx.productprice.BeanProvider.getWritableDb;

public abstract class AbstractRepository<E extends Entity> {

    public void save(E item) {
        SQLiteDatabase writableDb = getWritableDb();
        Long id = writableDb.insert(getTableName(), null, item.getContentValues());
        item.setId(id);
        writableDb.close();
    }

    public List<E> findAll() {
        Cursor cursor = getReadableDb().query(getTableName(), null, null, null, null, null, null);
        List<E> list = new LinkedList<E>();
        while (cursor.moveToNext()) {
            list.add(createItem(cursor));
        }
        return list;
    }

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

    public abstract E createItem(Cursor cursor);

    public abstract String getTableName();
}
