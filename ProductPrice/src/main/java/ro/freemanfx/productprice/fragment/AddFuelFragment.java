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

import com.appspot.wise_logic_658.fuelprice.model.FuelPrice;
import com.appspot.wise_logic_658.fuelprice.model.Place;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.SelectLocationActivity;
import rx.functions.Action1;

import static ro.freemanfx.productprice.AppContext.getFuel;
import static ro.freemanfx.productprice.AppContext.getPlace;
import static ro.freemanfx.productprice.BeanProvider.connectivityUtil;
import static ro.freemanfx.productprice.BeanProvider.displayNoConnectivityMessage;

public class AddFuelFragment extends Fragment {
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.place)
    TextView place;

    @InjectView(R.id.price)
    TextView price;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.add_product);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addfuel_fragment, container, false);
        ButterKnife.inject(this, view);
        name.setText(getString(getFuel().categoryResId) + " " + getString(getFuel().resId));
        return view;
    }

    @OnClick(R.id.select_location_on_map)
    public void selectLocationOnMap() {
        if (!connectivityUtil().isConnected()) {
            displayNoConnectivityMessage();
            return;
        }
        Intent intent = new Intent(getActivity(), SelectLocationActivity.class);
        startActivityForResult(intent, Constants.SELECT_LOCATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            place.setText(getPlace().getName());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.add)
    public void onAdd() {
        if (!validInput()) {
            return;
        }
        double price = Double.parseDouble(this.price.getText().toString());
        Place place = new Place().setName(getPlace().getName())
                .setLatitude(getPlace().getLocation().latitude)
                .setLongitude(getPlace().getLocation().longitude);
        FuelPrice fuelPrice = new FuelPrice().setPrice(price).setPlace(place).setFuel(getFuel().type);
        BeanProvider.fuelService().add(fuelPrice)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(getActivity(), "Price added", Toast.LENGTH_SHORT).show();
                    }
                });
        getActivity().finish();
    }

    private boolean validInput() {
        String priceString = price.getText().toString();

        if (invalidString(priceString)) {
            Toast.makeText(getActivity(), "Price is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (getPlace() == null) {
            Toast.makeText(getActivity(), "Please select a place!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean invalidString(String nameString) {
        return nameString == null || nameString.isEmpty();
    }
}
