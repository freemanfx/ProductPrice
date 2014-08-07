package ro.freemanfx.productprice.fragment;

import android.app.Activity;
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
import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.SelectLocationActivity;
import ro.freemanfx.productprice.domain.Product;
import rx.functions.Action1;

import static ro.freemanfx.productprice.BeanProvider.productService;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class AddProductFragment extends Fragment implements Constants {
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.barcode)
    TextView barcode;

    @InjectView(R.id.price)
    TextView price;

    @InjectView(R.id.place)
    TextView place;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.add_product);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addproduct_fragment, container, false);
        ButterKnife.inject(this, view);

        String barcodeString = getActivity().getIntent().getStringExtra(BARCODE);
        barcode.setText(barcodeString);

        setProductNameIfExists(barcodeString);
        return view;
    }

    private void setProductNameIfExists(String barcodeString) {
        productService().findProduct(barcodeString)
                .observeOn(mainThread())
                .subscribe(new Action1<Product>() {
                    @Override
                    public void call(Product product) {
                        if (product != null) {
                            name.setText(product.getName());
                        } else {
                            name.setHint(getString(R.string.name_for_product));
                            name.setEnabled(true);
                        }
                    }
                });
    }

    @OnClick(R.id.select_location_on_map)
    public void selectLocationOnMap() {
        Intent intent = new Intent(getActivity(), SelectLocationActivity.class);
        startActivityForResult(intent, Constants.SELECT_LOCATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            place.setText(AppContext.getPlace().getName());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.add)
    public void onAdd() {
        if (!validInput()) {
            return;
        }
        Product product = new Product(name.getText().toString(), barcode.getText().toString());
        double price = Double.parseDouble(this.price.getText().toString());
        productService()
                .addProduct(product, AppContext.getPlace(), price)
                .subscribe();
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

        if (AppContext.getPlace() == null) {
            Toast.makeText(getActivity(), "Please select a place!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean invalidString(String nameString) {
        return nameString == null || nameString.isEmpty();
    }
}
