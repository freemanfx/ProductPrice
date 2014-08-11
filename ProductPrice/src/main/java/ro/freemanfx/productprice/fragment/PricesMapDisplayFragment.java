package ro.freemanfx.productprice.fragment;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.domain.ProductPrice;

public class PricesMapDisplayFragment extends SupportMapFragment {

    private GoogleMap map;

    @Override
    public void onResume() {
        super.onResume();
        map = getMap();
        map.setMyLocationEnabled(true);
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

    public BitmapDescriptor makeIconForPrice(Double price) {
        IconGenerator iconGenerator = new IconGenerator(getActivity());
        iconGenerator.setStyle(IconGenerator.STYLE_GREEN);
        return BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon(price.toString()));
    }
}
