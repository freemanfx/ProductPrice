package ro.freemanfx.productprice.domain;

public class Product {
    private final String name;
    private final String barcode;

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!barcode.equals(product.barcode)) return false;
        if (!name.equals(product.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + barcode.hashCode();
        return result;
    }
}
