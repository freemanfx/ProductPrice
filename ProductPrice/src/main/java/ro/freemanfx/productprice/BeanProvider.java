package ro.freemanfx.productprice;

import android.content.Context;
import android.widget.Toast;

import com.appspot.wise_logic_658.place.Place;
import com.appspot.wise_logic_658.productprice.Productprice;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import ro.freemanfx.productprice.service.FuelService;
import ro.freemanfx.productprice.service.IProductService;
import ro.freemanfx.productprice.service.ProductServiceGAE;

public class BeanProvider {
    private static Context context;

    private static IProductService productService;
    private static ReactiveLocationProvider locationProvider;
    private static Productprice productPriceService;
    private static ConnectivityUtil connectivityUtil;
    private static Place placeService;
    private static FuelService fuelService;

    public static void init(Context context) {
        BeanProvider.context = context;
    }

    public static IProductService productService() {
        if (productService == null) {
            productService = new ProductServiceGAE();
        }
        return productService;
    }

    public static ReactiveLocationProvider locationProvider() {
        if (locationProvider == null) {
            locationProvider = new ReactiveLocationProvider(context);
        }
        return locationProvider;
    }

    public static Productprice productPriceService() {
        if (productPriceService == null) {
            productPriceService = new Productprice.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null).build();
        }
        return productPriceService;
    }

    public static Place placeService() {
        if (placeService == null) {
            placeService = new Place.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null).build();
        }
        return placeService;
    }

    public static ConnectivityUtil connectivityUtil() {
        if (connectivityUtil == null) {
            connectivityUtil = new ConnectivityUtil(context);
        }
        return connectivityUtil;
    }

    public static void displayNoConnectivityMessage() {
        Toast.makeText(context, context.getString(R.string.internet_connection_needed_for_operation), Toast.LENGTH_SHORT).show();
    }

    public static FuelService fuelService() {
        if (fuelService == null) {
            fuelService = new FuelService();
        }
        return fuelService;
    }
}
