package ro.freemanfx.productprice.service;

import ro.freemanfx.productprice.R;

public interface FuelTypes {
    FuelResource[] GASOLINE_TYPES = {
            new FuelResource(R.string.gasoline, "GASOLINE_STANDARD_95", R.string.GASOLINE_STANDARD_95),
            new FuelResource(R.string.gasoline, "GASOLINE_EXTRA_99", R.string.GASOLINE_EXTRA_99)
    };

    FuelResource[] DIESEL_TYPES = {
            new FuelResource(R.string.diesel, "DIESEL_STANDARD", R.string.DIESEL_STANDARD),
            new FuelResource(R.string.diesel, "DIESEL_EXTRA", R.string.DIESEL_EXTRA)
    };

    FuelResource[] OTHERS = {
            new FuelResource(R.string.empty, "OTHER_LPG", R.string.LPG)
    };

    static class FuelResource {
        public final int categoryResId;
        public final int resId;
        public final String type;

        public FuelResource(int categoryResId, String type, int resId) {
            this.categoryResId = categoryResId;
            this.type = type;
            this.resId = resId;
        }
    }
}
