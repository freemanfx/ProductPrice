package ro.freemanfx.productprice.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;

import static ro.freemanfx.productprice.BeanProvider.productPriceRepository;
import static ro.freemanfx.productprice.Constants.BARCODE;

public class FindProductFragment extends ListFragment {

    private String barcode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcode = getActivity().getIntent().getStringExtra(BARCODE);
        Product product = BeanProvider.productRepository().findByBarcode(barcode);
        if (product == null) {
            Toast.makeText(getActivity(), "There are no records of this product!", Toast.LENGTH_LONG);
            getActivity().finish();
        } else {
            setTitleForProduct(product.getName());
        }
    }

    private void setTitleForProduct(String title) {
        getActivity().getActionBar().setTitle(title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setListAdapter(new ProductsAdapter(barcode));
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), "Click on " + position, Toast.LENGTH_SHORT).show();
    }

    private class ProductsAdapter extends ArrayAdapter<ProductPrice> {
        public ProductsAdapter(String barcode) {
            super(getActivity(), R.layout.product_price_list_item);
            addProductPrices(barcode);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.product_price_list_item, parent, false);
            }
            ProductPrice item = getItem(position);

            TextView place = (TextView) view.findViewById(R.id.place);
            place.setText(item.getPlace().getName());

            TextView viewById = (TextView) view.findViewById(R.id.price);
            viewById.setText(item.getPrice().toString());
            return view;
        }

        private void addProductPrices(String barcode) {
            //TODO: make this run on background thread ( rxJava ? )
            List<ProductPrice> byProduct = productPriceRepository().findByProductBarcode(barcode);
            addAll(byProduct);
            notifyDataSetChanged();
        }
    }
}
