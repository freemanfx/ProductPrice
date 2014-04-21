package ro.freemanfx.productprice.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
        if(!launched){
            launched = true;
            ScanUtil.scanProductCode(this);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            String barcode = result.getContents();

            Intent intent = new Intent(this, AddProductActivity.class);
            intent.putExtra(BARCODE, barcode);
            startActivity(intent);
            finish();
        }
    }
}
