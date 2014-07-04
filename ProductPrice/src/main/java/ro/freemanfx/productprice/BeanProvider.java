package ro.freemanfx.productprice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ro.freemanfx.productprice.domain.ProductRepository;
import ro.freemanfx.productprice.infrastructure.DatabaseHelper;

public class BeanProvider {
    private static Context context;

    private static DatabaseHelper databaseHelper;
    private static ProductRepository productRepository;

    public static void init(Context context) {
        BeanProvider.context = context;
    }

    public static SQLiteDatabase getWritableDb() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper.getWritableDb();
    }

    public static SQLiteDatabase getReadableDb() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper.getReadableDatabase();
    }

    public static ProductRepository productRepository() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
        }
        return productRepository;
    }
}
