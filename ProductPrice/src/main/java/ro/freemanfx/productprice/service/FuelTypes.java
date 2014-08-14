package ro.freemanfx.productprice.service;

import ro.freemanfx.productprice.R;

public interface FuelTypes {
    FuelResource[] GASOLINE_TYPES = {
            new FuelResource("GASOLINE_STANDARD_95", R.string.GASOLINE_STANDARD_95),
            new FuelResource("GASOLINE_EXTRA_99", R.string.GASOLINE_EXTRA_99)
    };

    FuelResource[] DIESEL_TYPES = {
            new FuelResource("DIESEL_STANDARD", R.string.DIESEL_STANDARD),
            new FuelResource("DIESEL_EXTRA", R.string.DIESEL_EXTRA)
    };

    static class FuelResource {
        public final int resId;
        public final String type;

        public FuelResource(String type, int resId) {
            this.type = type;
            this.resId = resId;
        }
    }
}
