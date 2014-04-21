package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;

import ro.freemanfx.productprice.fragment.AddProductFragment;

public class AddProductActivity extends SingleFragmentActivity {

    @Override
    Fragment createFragment() {
        return new AddProductFragment();
    }
}
