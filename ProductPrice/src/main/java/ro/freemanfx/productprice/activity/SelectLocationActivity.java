package ro.freemanfx.productprice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.fragment.SelectLocationMap;

public class SelectLocationActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new SelectLocationMap();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.select_location_for_product);
    }
}
