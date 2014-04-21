package ro.freemanfx.productprice.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;

public class AddProductFragment extends Fragment implements Constants {
    @InjectView(R.id.barcode)
    TextView barcode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addproduct_fragment, container, false);
        ButterKnife.inject(this, view);

        String barcodeString = getActivity().getIntent().getStringExtra(BARCODE);
        barcode.setText(barcodeString);
        return view;
    }

}
