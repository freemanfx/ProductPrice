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
    private MarkerOptions marker;

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
            marker = new MarkerOptions()
                    .draggable(true)
                    .position(new LatLng(location.getLatitude(), location.getLongitude()));
            map.addMarker(marker);
        }
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
        marker.position(latLng);
    }
}
