package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    Fragment createFragment() {
        return new MainFragment();
    }
}
