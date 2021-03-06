package ro.freemanfx.productprice.service;

import java.util.List;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;
import rx.Observable;

public interface IProductService {
    Observable<Product> findProduct(String barcode);
    Observable<String> addProduct(Product product, Place place, Double price);

    Observable<List<ProductPrice>> findByBarCode(String barcode);

    Observable<List<Place>> findAllPlaces();
}
