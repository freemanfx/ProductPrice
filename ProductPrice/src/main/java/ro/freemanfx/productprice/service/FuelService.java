package ro.freemanfx.productprice.service;

import com.appspot.wise_logic_658.fuelprice.Fuelprice;
import com.appspot.wise_logic_658.fuelprice.model.FuelPrice;
import com.appspot.wise_logic_658.fuelprice.model.FuelPriceCollection;
import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ro.freemanfx.productprice.domain.Place;
import rx.Observable;
import rx.Subscriber;

import static rx.Observable.create;
import static rx.schedulers.Schedulers.io;

public class FuelService {
    private Fuelprice service = new Fuelprice.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null).build();

    public Observable<String> add(final FuelPrice fuelPrice) {
        return create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Void execute = service.add(fuelPrice).execute();
                    if (execute != null) {
                        subscriber.onNext(execute.toString());
                    }
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(io());
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
        }).subscribeOn(io());
    }

    public Observable<List<Place>> allGasStations() {
        return create(new Observable.OnSubscribe<List<Place>>() {
            @Override
            public void call(Subscriber<? super List<Place>> subscriber) {
                try {
                    List<com.appspot.wise_logic_658.fuelprice.model.Place> items = service.allGasStations().execute().getItems();
                    List<Place> localPlaces = new LinkedList<Place>();
                    if (items != null) {
                        for (com.appspot.wise_logic_658.fuelprice.model.Place place : items) {
                            localPlaces.add(new Place(place.getName(), new LatLng(place.getLatitude(), place.getLongitude())));
                        }
                    }
                    subscriber.onNext(localPlaces);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(io());
    }
}
