package ro.freemanfx.productprice.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.ScanUtil;

public class ScanProductActivity extends SingleFragmentActivity {
    boolean launched = false;

    @Override
    Fragment createFragment() {
        return new Fragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!launched) {
            launched = true;
            ScanUtil.scanProductCode(this);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String barcode = result.getContents();
            if (!ScanUtil.validBarcode(barcode)) {
                Toast.makeText(this, "Error while scanning the product or invalid format! Try again !", Toast.LENGTH_LONG).show();
                finish();
            }
            Intent intent = new Intent(this, getActivityToStartAfterScan());
            intent.putExtra(BARCODE, barcode);
            startActivity(intent);
            finish();
        }
    }

    private Class<? extends SingleFragmentActivity> getActivityToStartAfterScan() {
        String scanIntent = getIntent().getStringExtra(SCAN_INTENT);
        if (Constants.SCAN_INTENT_ADD.equals(scanIntent)) {
            return AddProductActivity.class;
        }
        if (Constants.SCAN_INTENT_FIND.equals(scanIntent)) {
            return FindProductActivity.class;
        }

        return AddProductActivity.class;
    }
}
