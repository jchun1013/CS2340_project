package com.example.frys.waters.controllers;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.frys.waters.R;
import com.example.frys.waters.model.Location;
import com.example.frys.waters.model.WaterSourceReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.example.frys.waters.controllers.ViewPurityReportActivity.selectedReport2;

public class ChoosePurityHistoryActivity extends AppCompatActivity {

    static Spinner chooseLocationviewSpinner;
    static Spinner choosePPMviewSpinner;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    Set<String> locationSet = new HashSet<>();
    List<String> locationlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_purity_history);

        List<String> ppmType = Arrays.asList("Virus", "Contaminant");

        getAllLocation();

        choosePPMviewSpinner = (Spinner) findViewById(R.id.ppmTypeSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ppmType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePPMviewSpinner.setAdapter(dataAdapter);

        Button next = (Button) findViewById(R.id.nextButton_choose);
        Button cancel = (Button) findViewById(R.id.cancelButton_choose);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosePurityHistoryActivity.this, ChoosePurityYearActivity.class));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public String getAddress(Location loc) {
        Geocoder gc = new Geocoder(ChoosePurityHistoryActivity.this, Locale.getDefault());
        List<Address> addressList;
        String returnAddress = "";
        try {
            addressList = gc.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                String subLocality = address.getSubLocality();
                String postalCode = address.getPostalCode();
                String locality = address.getLocality();
                String premises = address.getPremises();
                String country = address.getCountryName();
                returnAddress += country;
                if (locality != null) {
                    returnAddress += " " + locality;
                }
                if (subLocality != null) {
                    returnAddress += " " + subLocality;
                }
                if (postalCode != null) {
                    returnAddress += " " + postalCode;
                }
                if (premises != null) {
                    returnAddress += " " + premises;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnAddress;
    }

    public void getAllLocation() {
        databaseReference.child("purity report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    //Location childValue = child.getValue(Location.class);
                    locationSet.add(getAddress(new Location(Double.parseDouble(child.child("location").child("latitude").getValue().toString())
                            , Double.parseDouble(child.child("location").child("longitude").getValue().toString()))));
                }
                locationlist = new ArrayList<>(locationSet);

                chooseLocationviewSpinner = (Spinner) findViewById(R.id.chooseLocationSpinner);
                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(ChoosePurityHistoryActivity.this,
                        android.R.layout.simple_spinner_item, locationlist);
                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                chooseLocationviewSpinner.setAdapter(dataAdapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}