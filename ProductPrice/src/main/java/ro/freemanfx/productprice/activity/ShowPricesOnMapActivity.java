package ro.freemanfx.productprice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.PricesMapDisplayFragment;

public class ShowPricesOnMapActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    Fragment createFragment() {
        return new PricesMapDisplayFragment();
    }
}
