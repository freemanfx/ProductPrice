package ro.freemanfx.productprice.domain;

import org.joda.money.Money;
import org.joda.time.DateTime;

public class ProductPrice {
    private Long id;

    private final Product product;
    private final Place place;
    private final Money price;
    private final DateTime date;

    public ProductPrice(Product product, Place place, Money price, DateTime date) {
        this.product = product;
        this.place = place;
        this.price = price;
        this.date = date;
    }
}
