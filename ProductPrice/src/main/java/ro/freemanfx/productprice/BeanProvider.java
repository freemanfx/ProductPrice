package ro.freemanfx.productprice;

import android.content.Context;

public class BeanProvider {
    private static Context context;

    public static void init(Context context) {
        BeanProvider.context = context;
    }
}
