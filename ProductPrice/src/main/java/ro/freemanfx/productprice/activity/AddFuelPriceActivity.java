package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.AddFuelFragment;

public class AddFuelPriceActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new AddFuelFragment();
    }
}
