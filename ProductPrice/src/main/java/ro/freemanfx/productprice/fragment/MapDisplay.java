package ro.freemanfx.productprice.fragment;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ro.freemanfx.productprice.Constants;
import ro.freemanfx.productprice.domain.ProductPrice;

import static ro.freemanfx.productprice.BeanProvider.productPriceRepository;

public class MapDisplay extends SupportMapFragment {

    private GoogleMap map;

    @Override
    public void onResume() {
        super.onResume();
        map = getMap();
        map.setMyLocationEnabled(true);
        String barcode = getActivity().getIntent().getStringExtra(Constants.BARCODE);
        List<ProductPrice> prices = productPriceRepository().findByProductBarcode(barcode);
        for (ProductPrice productPrice : prices) {
            map.addMarker(
                    new MarkerOptions()
                            .position(productPrice.getPlace().getLocation())
                            .title(productPrice.getPrice().toString())
                            .snippet(productPrice.getPlace().getName())
            );
        }
    }
}
