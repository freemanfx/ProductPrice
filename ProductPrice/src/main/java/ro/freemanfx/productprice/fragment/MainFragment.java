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
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.ScanProductActivity;

public class MainFragment extends Fragment implements Constants {

    @InjectView(R.id.find)
    Button find;
    @InjectView(R.id.add)
    Button add;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.inject(this, view);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanFor(SCAN_INTENT_FIND);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanFor(SCAN_INTENT_ADD);
            }
        });
        return view;
    }

    private void startScanFor(String scanIntent) {
        Intent intent = new Intent(getActivity(), ScanProductActivity.class);
        intent.putExtra(Constants.SCAN_INTENT, scanIntent);
        startActivity(intent);
    }
}
