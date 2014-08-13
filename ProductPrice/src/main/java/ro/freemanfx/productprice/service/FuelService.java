package ro.freemanfx.productprice.service;

import com.appspot.wise_logic_658.fuelprice.Fuelprice;
import com.appspot.wise_logic_658.fuelprice.model.FuelPrice;
import com.appspot.wise_logic_658.fuelprice.model.FuelPriceCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static rx.Observable.create;

public class FuelService {
    private Fuelprice service = new Fuelprice.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null).build();

    public Observable<String> add(final FuelPrice fuelPrice) {
        return create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Void execute = service.add(fuelPrice).execute();
                    subscriber.onNext(execute.toString());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<List<FuelPrice>> findByFuel(final String fuel) {
        return create(new Observable.OnSubscribe<List<FuelPrice>>() {
            @Override
            public void call(Subscriber<? super List<FuelPrice>> subscriber) {
                try {
                    FuelPriceCollection resultCollection = service.findPrices(fuel).execute();
                    subscriber.onNext(resultCollection.getItems());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
