package ro.freemanfx.productprice;

import android.content.Context;

import org.orman.dbms.Database;
import org.orman.dbms.sqliteandroid.SQLiteAndroid;
import org.orman.mapper.MappingSession;

import ro.freemanfx.productprice.repository.ProductRepository;

public class BeanProvider {
    public static final String DATABASE_NAME = "productPrice.db";
    private static final int DB_VERSION = 1;

    private static Context context;
    private static Database ormDatabase;
    private static ProductRepository productRepository;

    public static void init(Context newContext) {
        context = newContext;
        ormDatabase();
    }

    public static ProductRepository productRepository() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
        }

        return productRepository;
    }

    public static Database ormDatabase() {
        if (ormDatabase == null) {
            ormDatabase = new SQLiteAndroid(context, DATABASE_NAME, DB_VERSION);
            MappingSession.registerDatabase(ormDatabase);
        }

        return ormDatabase;
    }
}
