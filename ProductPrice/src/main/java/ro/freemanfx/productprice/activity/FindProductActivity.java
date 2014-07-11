package ro.freemanfx.productprice.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.fragment.FindProductFragment;

public class FindProductActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new FindProductFragment();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prices_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_on_map:
                showAllOnMap(getIntent().getStringExtra(BARCODE));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAllOnMap(String barcode) {
        Intent intent = new Intent(this, ShowOnMapActivity.class);
        intent.putExtra(BARCODE, barcode);
        startActivity(intent);
    }
}
