package ro.freemanfx.productprice.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.domain.Place;

public class SelectLocationMap extends SupportMapFragment implements GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnInfoWindowClickListener {
    private GoogleMap map;
    private Marker marker;

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
    }

    @Override
    public void onMyLocationChange(Location location) {
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        addMarkerAt(position);
        map.animateCamera(CameraUpdateFactory.newLatLng(position));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        map.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        moveMarker(latLng);
    }

    @Override
    public void onInfoWindowClick(final Marker marker) {
        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(input)
                .setTitle(R.string.enter_place_name)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Place place = new Place(input.getText().toString(), marker.getPosition());
                        AppContext.setPlace(place);
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                    }
                });

        builder.create().show();
    }

    private void moveMarker(LatLng latLng) {
        addMarkerAt(latLng);
        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
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
                .title("New place")
                .snippet("Touch to select place")
                .position(position);
    }
}
