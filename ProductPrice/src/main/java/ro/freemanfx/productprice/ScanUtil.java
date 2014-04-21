package ro.freemanfx.productprice;

import android.app.Activity;

import com.google.zxing.integration.android.IntentIntegrator;

public class ScanUtil {

    public static void scanProductCode(Activity activityForResult){
        IntentIntegrator integrator = new IntentIntegrator(activityForResult);
        integrator.addExtra("SCAN_WIDTH", 800);
        integrator.addExtra("SCAN_HEIGHT", 200);
        integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
        integrator.addExtra("PROMPT_MESSAGE", "Scan the product bar code");
        integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
    }
}
