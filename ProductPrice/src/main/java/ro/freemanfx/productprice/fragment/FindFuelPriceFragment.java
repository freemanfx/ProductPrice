package ro.freemanfx.productprice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appspot.wise_logic_658.fuelprice.model.FuelPrice;

import java.util.List;

import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.FindFuelPriceActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static ro.freemanfx.productprice.BeanProvider.fuelService;

public class FindFuelPriceFragment extends CustomListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        listView.setAdapter(new FindFuelPriceFragment.FuePriceAdapter());
        return view;
    }

    private void setMapButtonVisibility(boolean visibility) {
        FindFuelPriceActivity activity = (FindFuelPriceActivity) getActivity();
        activity.getShowMapMenuItem().setVisible(visibility);
    }

    private class FuePriceAdapter extends ArrayAdapter<FuelPrice> {
        public FuePriceAdapter() {
            super(getActivity(), R.layout.product_price_list_item);
            populateList();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.product_price_list_item, parent, false);
            }
            FuelPrice item = getItem(position);

            TextView placeText = (TextView) view.findViewById(R.id.place);
            placeText.setText(item.getPlace().getName());

            TextView priceText = (TextView) view.findViewById(R.id.price);
            priceText.setText(item.getPrice().toString());
            return view;
        }

        private void populateList() {
            fuelService().findByFuel(AppContext.getFuel().type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<FuelPrice>>() {
                        @Override
                        public void call(List<FuelPrice> fuelPrices) {
                            AppContext.setFuelPrices(fuelPrices);
                            if (fuelPrices != null) {
                                addAll(fuelPrices);
                                notifyDataSetChanged();
                                setMapButtonVisibility(true);
                                showListAndHideProgressBar();
                            } else {
                                showNoResults();
                            }
                            dataLoaded = true;
                        }
                    });
        }
    }
}
