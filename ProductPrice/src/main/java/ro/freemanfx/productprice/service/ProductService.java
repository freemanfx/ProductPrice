package ro.freemanfx.productprice.service;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.Product;
import ro.freemanfx.productprice.domain.ProductPrice;

import static ro.freemanfx.productprice.BeanProvider.placeRepository;
import static ro.freemanfx.productprice.BeanProvider.productPriceRepository;
import static ro.freemanfx.productprice.BeanProvider.productRepository;

public class ProductService {

    public void addProduct(Product product, Place place, Double price) {
        saveProductIfNeeded(product);
        savePlaceIfNeeded(place);

        ProductPrice productPrice = new ProductPrice(product, place, price);
        productPriceRepository().save(productPrice);
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
