package ro.freemanfx.productprice.fragment;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectLocationMap extends SupportMapFragment implements GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
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
            map.getUiSettings().setZoomControlsEnabled(false);

            map.setOnMyLocationChangeListener(this);
            map.setOnMarkerDragListener(this);
            map.setOnMapClickListener(this);
            map.setOnMapLongClickListener(this);
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        if (marker == null) {
            MarkerOptions markerOptions = createDraggableMarker()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()));
            marker = map.addMarker(markerOptions);
            map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        }
    }

    private MarkerOptions createDraggableMarker() {
        return new MarkerOptions()
                .draggable(true);
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
    public void onMapClick(LatLng latLng) {
        latLng.toString();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        moveMarker(latLng);
    }

    private void moveMarker(LatLng latLng) {
        marker.setPosition(latLng);
        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
