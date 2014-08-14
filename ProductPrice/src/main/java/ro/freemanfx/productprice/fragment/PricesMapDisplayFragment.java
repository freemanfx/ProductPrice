package ro.freemanfx.productprice.fragment;


import com.appspot.wise_logic_658.fuelprice.model.FuelPrice;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.domain.ProductPrice;

import static ro.freemanfx.productprice.AppContext.getFuel;
import static ro.freemanfx.productprice.Constants.FUEL_PRICES;
import static ro.freemanfx.productprice.Constants.PRODUCT_PRICES;

public class PricesMapDisplayFragment extends SupportMapFragment {
    private GoogleMap map;

    @Override
    public void onResume() {
        super.onResume();
        map = getMap();
        map.setMyLocationEnabled(true);
        String pricesType = getActivity().getIntent().getStringExtra(Constants.PRICE_TYPES);
        if (PRODUCT_PRICES.equals(pricesType)) {
            addProductPriceMarkers();
        } else if (FUEL_PRICES.equals(pricesType)) {
            setFuelTypeAsTitle();
            addGasPriceMarkers();
        }
    }

    private void setFuelTypeAsTitle() {
        String first = getString(getFuel().categoryResId);
        String second = getString(getFuel().resId);
        getActivity().setTitle(first + " " + second);
    }

    private void addProductPriceMarkers() {
        for (ProductPrice productPrice : AppContext.getProductPrices()) {
            map.addMarker(new MarkerOptions()
                    .position(productPrice.getPlace().getLocation())
                    .title(productPrice.getPrice().toString())
                    .icon(makeIconForPrice(productPrice.getPrice()))
                    .snippet(productPrice.getPlace().getName()));
        }
        if (AppContext.getProductPrices().size() > 0) {
            ProductPrice productPrice = AppContext.getProductPrices().get(0);
            getActivity().setTitle(productPrice.getProduct().getName());
        }
    }

    private void addGasPriceMarkers() {
        for (FuelPrice fuelPrice : AppContext.getFuelPrices()) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(fuelPrice.getPlace().getLatitude(), fuelPrice.getPlace().getLongitude()))
                    .title(fuelPrice.getPrice().toString())
                    .icon(makeIconForPrice(fuelPrice.getPrice()))
                    .snippet(fuelPrice.getPlace().getName()));
        }
    }

    public BitmapDescriptor makeIconForPrice(Double price) {
        IconGenerator iconGenerator = new IconGenerator(getActivity());
        iconGenerator.setStyle(IconGenerator.STYLE_GREEN);
        return BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon(price.toString()));
    }
}
