package ro.freemanfx.productprice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.fragment.FindFuelPriceFragment;

import static ro.freemanfx.productprice.AppContext.getFuel;

public class FindFuelPriceActivity extends SingleFragmentActivity {
    private MenuItem showMapMenuItem;

    @Override
    Fragment createFragment() {
        return new FindFuelPriceFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFuelTypeAsTitle();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prices_list_menu, menu);
        showMapMenuItem = menu.getItem(0);
        showMapMenuItem.setVisible(false);
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
        intent.putExtra(Constants.PRICE_TYPES, Constants.FUEL_PRICES);
        startActivity(intent);
    }

    private void setFuelTypeAsTitle() {
        String first = getString(getFuel().categoryResId);
        String second = getString(getFuel().resId);
        setTitle(first + " " + second);
    }

    public MenuItem getShowMapMenuItem() {
        return showMapMenuItem;
    }
}
