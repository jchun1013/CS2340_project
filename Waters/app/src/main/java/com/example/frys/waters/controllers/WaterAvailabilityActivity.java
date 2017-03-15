package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.frys.waters.R;

import com.example.frys.waters.model.Location;
import com.example.frys.waters.model.UserType;
import com.example.frys.waters.model.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;
import static com.example.frys.waters.controllers.WaterSourceReportActivity.newLocation;

public class WaterAvailabilityActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    private Marker prevMarker;
    private List<LatLng> markerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_availability);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<WaterSourceReport> reportList = sourceReports;
        for (com.example.frys.waters.model.WaterSourceReport r : sourceReports) {
            LatLng loc = new LatLng(r.getLocation().getLatitude(), r.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(r.getNameOfReporter()).snippet(r.getDateTime()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            System.out.println("HAAAAAAAAAALLLLLLLLOOOOOOO");
        }


        // Add a marker in Sydney and move the camera
        if (currentUser.getIsReporting() == true) {
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    // Creating a marker
                    MarkerOptions markerOptions = new MarkerOptions();

                    // Setting the position for the marker
                    markerOptions.position(latLng);

                    // Animating to the touched position
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    // Placing a marker on the touched position
                    mMap.addMarker(markerOptions);

                    newLocation.set_latitude((latLng.latitude));
                    newLocation.set_longitude((latLng.longitude));

                    currentUser.setIsReporting(false);
                    finish();
                }
            });
        } else {
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    double latitude = marker.getPosition().latitude;
                    double longitude = marker.getPosition().longitude;
                    if (prevMarker != null) {
                        //Set prevMarker back to default color
                        prevMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }

                    //leave Marker default color if re-click current Marker
                    if (!marker.equals(prevMarker)) {
                        prevMarker = marker;
                    }
                    prevMarker = marker;
                    return false;
                }

            });
        }
    }
}