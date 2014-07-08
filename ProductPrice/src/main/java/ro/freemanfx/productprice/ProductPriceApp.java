package ro.freemanfx.productprice;

import android.app.Application;

public class ProductPriceApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BeanProvider.init(getApplicationContext());
    }
}
