package ro.freemanfx.productprice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;

public abstract class SingleFragmentActivity extends FragmentActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, createFragment())
                .commit();
    }

    abstract Fragment createFragment();
}
