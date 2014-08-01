package ro.freemanfx.productprice.service;

import java.util.List;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;
import rx.Observable;
import rx.Subscriber;

import static ro.freemanfx.productprice.BeanProvider.placeRepository;
import static ro.freemanfx.productprice.BeanProvider.productPriceRepository;
import static ro.freemanfx.productprice.BeanProvider.productRepository;

public class ProductService implements IProductService {

    @Override
    public void addProduct(Product product, Place place, Double price) {
        saveProductIfNeeded(product);
        savePlaceIfNeeded(place);

        ProductPrice productPrice = new ProductPrice(product, place, price);
        productPriceRepository().save(productPrice);
    }

    @Override
    public Observable<List<ProductPrice>> findByBarCode(final String barcode) {
        return Observable.create(new Observable.OnSubscribe<List<ProductPrice>>() {
            @Override
            public void call(Subscriber<? super List<ProductPrice>> subscriber) {
                subscriber.onNext(productPriceRepository().findByProductBarcode(barcode));
                subscriber.onCompleted();
            }
        });
    }

    private void savePlaceIfNeeded(Place place) {
        if (!place.isPersisted()) {
            placeRepository().save(place);
        }
    }

    private void saveProductIfNeeded(Product product) {
        if (!product.isPersisted()) {
            productRepository().save(product);
        }
    }
}
