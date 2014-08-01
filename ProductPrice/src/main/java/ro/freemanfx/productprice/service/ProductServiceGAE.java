package ro.freemanfx.productprice.service;

import com.appspot.wise_logic_658.productprice.Productprice;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.util.List;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;
import rx.Observable;

public class ProductServiceGAE implements IProductService {
    @Override
    public void addProduct(Product product, Place place, Double price) {
        Productprice.Builder builder = new Productprice.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
        Productprice service = builder.build();

        com.appspot.wise_logic_658.productprice.model.Product productTo = new com.appspot.wise_logic_658.productprice.model.Product();
        productTo.setBarcode(product.getBarcode());
        productTo.setName(product.getName());

        com.appspot.wise_logic_658.productprice.model.Place placeTo = new com.appspot.wise_logic_658.productprice.model.Place();
        placeTo.setName(place.getName());
        placeTo.setLatitude(place.getLocation().latitude);
        placeTo.setLongitude(place.getLocation().longitude);

        com.appspot.wise_logic_658.productprice.model.ProductPrice data = new com.appspot.wise_logic_658.productprice.model.ProductPrice();
        data.setPrice(price);
        data.setProduct(productTo);
        data.setPlace(placeTo);

        try {
            service.add(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Observable<List<ProductPrice>> findByBarCode(String barcode) {
        return null;
    }
}
