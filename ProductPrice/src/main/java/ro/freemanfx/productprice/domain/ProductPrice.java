package ro.freemanfx.productprice.domain;

public class ProductPrice {
    private final Product product;
    private final Place place;
    private final Double price;

    public ProductPrice(Product product, Place place, Double price) {
        this.product = product;
        this.place = place;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPrice that = (ProductPrice) o;

        if (!place.equals(that.place)) return false;
        if (!price.equals(that.price)) return false;
        if (!product.equals(that.product)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + place.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    public Double getPrice() {
        return price;
    }

    public Place getPlace() {
        return place;
    }

    public Product getProduct() {
        return product;
    }
}
