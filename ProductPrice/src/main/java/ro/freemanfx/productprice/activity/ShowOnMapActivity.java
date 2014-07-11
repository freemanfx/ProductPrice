package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.MapDisplay;

public class ShowOnMapActivity extends SingleFragmentActivity {

    @Override
    Fragment createFragment() {
        return new MapDisplay();
    }
}
