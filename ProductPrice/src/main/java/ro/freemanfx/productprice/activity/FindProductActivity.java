package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.FindProductFragment;

public class FindProductActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new FindProductFragment();
    }
}
