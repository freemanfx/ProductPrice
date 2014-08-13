package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.FuelSelectionFragment;

public class FuelSelectionActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new FuelSelectionFragment();
    }
}
