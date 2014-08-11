package ro.freemanfx.productprice.service;

import com.appspot.wise_logic_658.productprice.Productprice;
import com.appspot.wise_logic_658.productprice.model.ProductPriceCollection;
import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;
import rx.Observable;
import rx.Subscriber;

import static ro.freemanfx.productprice.BeanProvider.placeService;
import static ro.freemanfx.productprice.BeanProvider.productPriceService;
import static rx.Observable.OnSubscribe;
import static rx.Observable.create;
import static rx.schedulers.Schedulers.io;

public class ProductServiceGAE implements IProductService {
    @Override
    public Observable<Product> findProduct(final String barcode) {
        return create(new OnSubscribe<Product>() {
            @Override
            public void call(Subscriber<? super Product> subscriber) {
                try {
                    com.appspot.wise_logic_658.productprice.model.Product product = productPriceService().findproduct(barcode).execute();
                    if (product != null) {
                        subscriber.onNext(new Product(product.getName(), product.getBarcode()));
                    } else {
                        subscriber.onNext(null);
                    }
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(io());
    }

    @Override
    public Observable<String> addProduct(Product product, Place place, Double price) {
        com.appspot.wise_logic_658.productprice.model.Product productTo = new com.appspot.wise_logic_658.productprice.model.Product();
        productTo.setBarcode(product.getBarcode());
        productTo.setName(product.getName());

        com.appspot.wise_logic_658.productprice.model.Place placeTo = new com.appspot.wise_logic_658.productprice.model.Place();
        placeTo.setName(place.getName());
        placeTo.setLatitude(place.getLocation().latitude);
        placeTo.setLongitude(place.getLocation().longitude);

        final com.appspot.wise_logic_658.productprice.model.ProductPrice data = new com.appspot.wise_logic_658.productprice.model.ProductPrice();
        data.setPrice(price);
        data.setProduct(productTo);
        data.setPlace(placeTo);

        return
                create(new OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            Void result = productPriceService().add(data).execute();
                            subscriber.onNext(result.toString());
                            subscriber.onCompleted();
                        } catch (IOException e) {
                            subscriber.onError(e);
                        }
                    }
                }).subscribeOn(io());
    }

    @Override
    public Observable<List<ProductPrice>> findByBarCode(final String barcode) {
        return create(new OnSubscribe<List<ProductPrice>>() {
            @Override
            public void call(Subscriber<? super List<ProductPrice>> subscriber) {
                try {
                    subscriber.onNext(getProductPricesFromCloud(barcode));
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(io());
    }

    @Override
    public Observable<List<Place>> findAllPlaces() {
        return create(new OnSubscribe<List<Place>>() {
            @Override
            public void call(Subscriber<? super List<Place>> subscriber) {
                try {
                    subscriber.onNext(getLocalPlacesFromAPI());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(io());
    }

    private List<Place> getLocalPlacesFromAPI() throws IOException {
        List<Place> localPlaces = new LinkedList<Place>();
        List<com.appspot.wise_logic_658.place.model.Place> places = placeService().all().execute().getItems();
        if (places != null) {
            for (com.appspot.wise_logic_658.place.model.Place place : places) {
                localPlaces.add(new Place(place.getName(), new LatLng(place.getLatitude(), place.getLongitude())));
            }
        }
        return localPlaces;
    }

    private List<ProductPrice> getProductPricesFromCloud(String barcode) throws IOException {
        final Productprice service = new Productprice.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null).build();

        ProductPriceCollection results = service.prices(barcode).execute();

        List<ProductPrice> localResults = new LinkedList<ProductPrice>();
        List<com.appspot.wise_logic_658.productprice.model.ProductPrice> items = results.getItems();
        if (items != null) {
            for (com.appspot.wise_logic_658.productprice.model.ProductPrice productPrice : items) {
                Product localProduct = new Product(productPrice.getProduct().getName(), productPrice.getProduct().getBarcode());
                Place localPlace = new Place(productPrice.getPlace().getName(), new LatLng(productPrice.getPlace().getLatitude(), productPrice.getPlace().getLongitude()));
                ProductPrice localResult = new ProductPrice(localProduct, localPlace, productPrice.getPrice());
                localResults.add(localResult);
            }
        }
        return localResults;
    }
}
