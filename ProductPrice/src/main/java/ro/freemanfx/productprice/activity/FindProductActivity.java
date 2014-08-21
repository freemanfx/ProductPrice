package ro.freemanfx.productprice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.fragment.FindProductFragment;

public class FindProductActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new FindProductFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prices_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_on_map:
                showAllOnMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAllOnMap() {
        Intent intent = new Intent(this, ShowPricesOnMapActivity.class);
        intent.putExtra(Constants.PRICE_TYPES, Constants.PRODUCT_PRICES);
        startActivity(intent);
    }
}
