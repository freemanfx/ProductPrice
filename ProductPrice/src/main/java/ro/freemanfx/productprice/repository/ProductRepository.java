package ro.freemanfx.productprice.repository;

import org.orman.mapper.C;
import org.orman.mapper.ModelQuery;

import ro.freemanfx.productprice.domain.Product;

public class ProductRepository {

    public void save(Product product) {
        product.insert();
    }

    public Product findByBarCode(String barcode) {
        return Product.fetchSingle(ModelQuery
                .select()
                .from(Product.class)
                .where(
                        C.eq(Product.class, "barcode", barcode)
                ).getQuery(), Product.class);
    }
}
