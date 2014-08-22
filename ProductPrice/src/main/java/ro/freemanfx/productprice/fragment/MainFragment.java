package ro.freemanfx.productprice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.IntegratedScanActivity;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.SelectFuelActivity;

import static ro.freemanfx.productprice.BeanProvider.connectivityUtil;
import static ro.freemanfx.productprice.BeanProvider.displayNoConnectivityMessage;

public class MainFragment extends Fragment implements Constants {

    @InjectView(R.id.find)
    Button find;
    @InjectView(R.id.add)
    Button add;
    @InjectView(R.id.fuels)
    Button fuels;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.inject(this, view);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectivityUtil().isConnected()) {
                    startScanFor(SCAN_INTENT_FIND);
                } else {
                    displayNoConnectivityMessage();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectivityUtil().isConnected()) {
                    AppContext.setPlace(null);
                    startScanFor(SCAN_INTENT_ADD);
                } else {
                    displayNoConnectivityMessage();
                }
            }
        });

        fuels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectFuelActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void startScanFor(String scanIntent) {
        Intent intent = new Intent(getActivity(), IntegratedScanActivity.class);
        intent.putExtra(Constants.SCAN_INTENT, scanIntent);
        startActivity(intent);
    }
}
