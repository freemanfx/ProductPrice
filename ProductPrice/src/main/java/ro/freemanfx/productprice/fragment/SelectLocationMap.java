package ro.freemanfx.productprice.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.BeanProvider;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.domain.Place;

import static android.widget.LinearLayout.LayoutParams;
import static android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
import static com.google.android.gms.maps.CameraUpdateFactory.newLatLng;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker;

public class SelectLocationMap extends SupportMapFragment implements GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnInfoWindowClickListener {
    private GoogleMap map;
    private Marker marker;
    private Map<String, Place> markerIdToPlaces = new HashMap<String, Place>();

    @Override
    public void onResume() {
        super.onResume();
        initMap();
    }

    private void initMap() {
        if (map == null) {
            map = getMap();
            map.setMyLocationEnabled(true);
            map.setOnMyLocationChangeListener(this);
            map.setOnMarkerDragListener(this);
            map.setOnMapLongClickListener(this);
            map.setOnInfoWindowClickListener(this);
        }

        addExistingPlaces();
    }

    private void addExistingPlaces() {
        List<Place> places = BeanProvider.placeRepository().findAll();
        for (Place place : places) {
            MarkerOptions markerOption = existingPlaceMarker(place);
            Marker marker = map.addMarker(markerOption);
            markerIdToPlaces.put(marker.getId(), place);
        }
    }

    private MarkerOptions existingPlaceMarker(Place place) {
        return new MarkerOptions()
                .title(place.getName())
                .snippet(getString(R.string.touch_to_select_place))
                .icon(defaultMarker(HUE_GREEN))
                .position(place.getLocation());
    }

    @Override
    public void onMyLocationChange(Location location) {
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        addMarkerAt(position);
        map.animateCamera(newLatLng(position));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        map.moveCamera(newLatLng(marker.getPosition()));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        moveMarker(latLng);
    }

    @Override
    public void onInfoWindowClick(final Marker marker) {
        Place existingPlace = markerIdToPlaces.get(marker.getId());
        if (existingPlace == null) {
            showInfoWindowForNewPlace(marker);
        } else {
            showInfoWindowForExistingPlace(existingPlace, marker);
        }
    }

    private void showInfoWindowForExistingPlace(final Place existingPlace, Marker marker) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.Select_this_place))
                .setMessage(existingPlace.getName())
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectPlaceAndFinish(existingPlace);
                    }
                });

        builder.create().show();
    }

    private void showInfoWindowForNewPlace(final Marker marker) {
        final EditText input = new EditText(getActivity());
        LayoutParams lp = new LayoutParams(
                MATCH_PARENT,
                MATCH_PARENT);
        input.setLayoutParams(lp);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(input)
                .setTitle(R.string.enter_place_name)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Place place = new Place(input.getText().toString(), marker.getPosition());
                        selectPlaceAndFinish(place);
                    }
                });

        builder.create().show();
    }

    private void selectPlaceAndFinish(Place place) {
        AppContext.setPlace(place);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    private void moveMarker(LatLng latLng) {
        addMarkerAt(latLng);
        map.animateCamera(newLatLng(latLng));
    }

    private void addMarkerAt(LatLng position) {
        if (marker == null) {
            MarkerOptions markerOptions = createDraggableMarkerWithPosition(position)
                    .position(position);
            marker = map.addMarker(markerOptions);
        } else {
            marker.setPosition(position);
        }
    }

    private MarkerOptions createDraggableMarkerWithPosition(LatLng position) {
        return new MarkerOptions()
                .draggable(true)
                .title(getString(R.string.New_place))
                .snippet(getString(R.string.touch_to_select_place))
                .position(position);
    }
}
