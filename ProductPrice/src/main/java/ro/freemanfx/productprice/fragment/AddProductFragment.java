package ro.freemanfx.productprice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.SelectLocationActivity;
import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.infrastructure.LocationHelper;

public class AddProductFragment extends Fragment implements Constants {
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.barcode)
    TextView barcode;

    @InjectView(R.id.price)
    TextView price;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addproduct_fragment, container, false);
        ButterKnife.inject(this, view);

        String barcodeString = getActivity().getIntent().getStringExtra(BARCODE);
        barcode.setText(barcodeString);

        return view;
    }

    @OnClick(R.id.select_location_on_map)
    public void selectLocationOnMap() {
        Intent intent = new Intent(getActivity(), SelectLocationActivity.class);
        startActivityForResult(intent, Constants.SELECT_LOCATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick(R.id.add)
    public void onAdd() {
        if (!validInput()) {
            return;
        }

        Place place = new Place("Home", LocationHelper.newLocation(10.22, 22.22));
        BeanProvider.productService().addProduct(new Product(name.getText().toString(), barcode.getText().toString()), place, Double.parseDouble(price.getText().toString()));
        getActivity().finish();
    }

    private boolean validInput() {
        String nameString = name.getText().toString();
        String priceString = price.getText().toString();
        String barcodeString = barcode.getText().toString();

        if (invalidString(nameString)) {
            Toast.makeText(getActivity(), "Name is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (invalidString(priceString)) {
            Toast.makeText(getActivity(), "Price is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (invalidString(barcodeString)) {
            Toast.makeText(getActivity(), "Barcode is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean invalidString(String nameString) {
        return nameString == null || nameString.isEmpty();
    }

}
