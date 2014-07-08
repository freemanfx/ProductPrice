package ro.freemanfx.productprice.infrastructure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "productprice.db";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase myWritableDb;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Product.getCreateTable());
        db.execSQL(Place.getCreateTable());
        db.execSQL(ProductPrice.getCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getWritableDb() {
        if ((myWritableDb == null) || (!myWritableDb.isOpen())) {
            myWritableDb = this.getWritableDatabase();
        }

        return myWritableDb;
    }

    @Override
    public void close() {
        super.close();
        if (myWritableDb != null) {
            myWritableDb.close();
            myWritableDb = null;
        }
    }
}
