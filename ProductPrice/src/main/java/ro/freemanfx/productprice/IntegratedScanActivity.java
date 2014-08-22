package ro.freemanfx.productprice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import ro.freemanfx.productprice.activity.AddProductActivity;
import ro.freemanfx.productprice.activity.FindProductActivity;
import ro.freemanfx.productprice.activity.SingleFragmentActivity;

import static java.util.Arrays.asList;
import static me.dm7.barcodescanner.zbar.BarcodeFormat.EAN13;
import static me.dm7.barcodescanner.zbar.BarcodeFormat.EAN8;

public class IntegratedScanActivity extends Activity implements ZBarScannerView.ResultHandler, Constants {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);
        mScannerView.setFormats(asList(EAN8, EAN13));
        setTitle(R.string.scan_product_barcode);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String barcode = rawResult.getContents();

        Intent intent = new Intent(this, getActivityToStartAfterScan());
        intent.putExtra(BARCODE, barcode);
        startActivity(intent);
        finish();
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