package ro.freemanfx.productprice;

import java.util.List;

import ro.freemanfx.productprice.domain.Place;
import ro.freemanfx.productprice.domain.ProductPrice;

public class AppContext {
    private static Place place;
    private static List<ProductPrice> productPrices;

    public static Place getPlace() {
        return place;
    }

    public static void setPlace(Place place) {
        AppContext.place = place;
    }

    public static List<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public static void setProductPrices(List<ProductPrice> productPrices) {
        AppContext.productPrices = productPrices;
    }
}
