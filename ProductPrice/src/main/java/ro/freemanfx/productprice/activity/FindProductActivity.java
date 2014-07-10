package ro.freemanfx.productprice.activity;

import android.support.v4.app.Fragment;
import android.view.Menu;

import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.fragment.FindProductFragment;

public class FindProductActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new FindProductFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prices_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
