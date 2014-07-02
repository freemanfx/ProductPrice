package ro.freemanfx.productprice.domain;

public class Product {
    private final String barcode;
    private final String name;

    public Product(String barcode, String name) {
        this.barcode = barcode;
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }
}
