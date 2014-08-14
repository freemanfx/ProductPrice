package ro.freemanfx.productprice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.fragment.FuelSelectionFragment;

public class FuelSelectionActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new FuelSelectionFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.select_fuel));
    }
}
