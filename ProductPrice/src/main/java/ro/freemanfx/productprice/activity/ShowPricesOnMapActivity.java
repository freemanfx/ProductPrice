package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.PricesMapDisplayFragment;

public class ShowPricesOnMapActivity extends SingleFragmentActivity {

    @Override
    Fragment createFragment() {
        return new PricesMapDisplayFragment();
    }
}
