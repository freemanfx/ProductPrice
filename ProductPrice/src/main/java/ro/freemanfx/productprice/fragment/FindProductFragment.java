package ro.freemanfx.productprice.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.domain.ProductPrice;

import static ro.freemanfx.productprice.Constants.BARCODE;

public class FindProductFragment extends ListFragment {

    private String barcode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        barcode = getActivity().getIntent().getStringExtra(BARCODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView) super.onCreateView(inflater, container, savedInstanceState);
        view.setAdapter(new ProductsAdapter(barcode));
        return view;
    }

    private class ProductsAdapter extends ArrayAdapter<ProductPrice> {
        public ProductsAdapter(String barcode) {
            super(getActivity(), R.layout.found_products_fragment);
        }

    }

}
