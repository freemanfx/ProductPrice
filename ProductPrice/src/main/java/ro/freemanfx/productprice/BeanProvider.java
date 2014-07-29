package ro.freemanfx.productprice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import ro.freemanfx.productprice.infrastructure.DatabaseHelper;
import ro.freemanfx.productprice.repository.PlaceRepository;
import ro.freemanfx.productprice.repository.ProductPriceRepository;
import ro.freemanfx.productprice.repository.ProductRepository;
import ro.freemanfx.productprice.service.ProductService;

public class BeanProvider {
    private static Context context;

    private static DatabaseHelper databaseHelper;
    private static ProductRepository productRepository;
    private static PlaceRepository placeRepository;
    private static ProductPriceRepository productPriceRepository;
    private static ProductService productService;
    private static ReactiveLocationProvider locationProvider;

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

    public static PlaceRepository placeRepository() {
        if (placeRepository == null) {
            placeRepository = new PlaceRepository();
        }
        return placeRepository;
    }

    public static ProductPriceRepository productPriceRepository() {
        if (productPriceRepository == null) {
            productPriceRepository = new ProductPriceRepository();
        }
        return productPriceRepository;
    }

    public static ProductService productService() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public static ReactiveLocationProvider locationProvider() {
        if (locationProvider == null) {
            locationProvider = new ReactiveLocationProvider(context);
        }
        return locationProvider;
    }
}
