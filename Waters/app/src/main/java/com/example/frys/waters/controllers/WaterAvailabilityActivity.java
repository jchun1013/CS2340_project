package com.example.frys.waters.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.frys.waters.R;

import com.example.frys.waters.model.Location;
import com.example.frys.waters.model.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.WaterSourceReportActivity.newLocation;

/**
 * This class displays water reports in google map
 */
public class WaterAvailabilityActivity extends FragmentActivity implements OnMapReadyCallback {

    private static GoogleMap mMap;
    private Marker prevMarker;

    private DatabaseReference databaseReference;
    private List<WaterSourceReport> reports;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        getAllReports();

        reports = new ArrayList<>();

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

        if (reports.size() > 0) {
            for (WaterSourceReport r : reports) {
                Location location = r.getLocation();
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(loc).title(r.getNameOfReporter()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            }
        }

        //adding marker during submitting report
        if (currentUser.getIsReporting()) {

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

    private void getAllReports() {
        databaseReference.child("source report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    WaterSourceReport childValue = child.getValue(WaterSourceReport.class);
                    childValue.setTypeOfWater(child.child("typeOfWater").getValue().toString());
                    childValue.setLocation(Double.parseDouble(child.child("location").child("latitude").getValue().toString())
                            , Double.parseDouble(child.child("location").child("longitude").getValue().toString()));
                    reports.add(childValue);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}