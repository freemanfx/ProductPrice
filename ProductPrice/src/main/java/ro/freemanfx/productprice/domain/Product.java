package ro.freemanfx.productprice.domain;

import org.orman.mapper.Model;
import org.orman.mapper.annotation.Entity;
import org.orman.mapper.annotation.PrimaryKey;

@Entity
public class Product extends Model<Product> {
    @PrimaryKey
    private String barcode;
    private String name;

    public Product(){}

    public Product(String barcode, String name) {
        this.barcode = barcode;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
