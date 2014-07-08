package ro.freemanfx.productprice.infrastructure;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class Entity {
    public static final String COLUMN_ID = "_ID";

    private Long id;

    public Entity(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
    }

    protected Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract ContentValues getContentValues();
}
