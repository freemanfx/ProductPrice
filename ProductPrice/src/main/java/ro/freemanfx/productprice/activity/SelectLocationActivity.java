package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.SelectLocationMap;

public class SelectLocationActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new SelectLocationMap();
    }
}
