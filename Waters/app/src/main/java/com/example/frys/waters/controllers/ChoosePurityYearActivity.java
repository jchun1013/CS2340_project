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
import com.example.frys.waters.model.WaterPurityReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.example.frys.waters.controllers.ChoosePurityHistoryActivity.chooseLocationviewSpinner;

public class ChoosePurityYearActivity extends AppCompatActivity {

    private Spinner chooseyearviewSpinner;
    static List<WaterPurityReport> reportsToShow;
    private List<String> yearList = new ArrayList<>();
    private Set<String> yearSet = new HashSet<>();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_purity_year);

        reportsToShow = new ArrayList<>();

        getAllYear();

        Button submit = (Button) findViewById(R.id.submitButton_choose1);
        Button cancel = (Button) findViewById(R.id.cancelButton_choose);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosePurityYearActivity.this, HistoryGraphActivity.class));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getAddress(double lat, double log) {
        Geocoder gc = new Geocoder(ChoosePurityYearActivity.this, Locale.getDefault());
        List<Address> addressList;
        //Location loc = new Location(lat, log);
        String returnAddress = "";
        try {
            addressList = gc.getFromLocation(lat, log, 1);
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

    private void getAllYear() {
        databaseReference.child("purity report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                String a = (String) chooseLocationviewSpinner.getSelectedItem();
                for (DataSnapshot child : children) {
                    if (getAddress(Double.parseDouble(child.child("location").child("latitude").getValue().toString())
                            , Double.parseDouble(child.child("location").child("longitude").getValue().toString())).indexOf(a) >= 0) {
                        yearSet.add(child.child("dateTime").getValue().toString().substring(0,4));
                        reportsToShow.add(child.getValue(WaterPurityReport.class));
                    }
                }
                yearList.addAll(yearSet);

                chooseyearviewSpinner = (Spinner) findViewById(R.id.yearSpinner);
                ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<>(ChoosePurityYearActivity.this,
                        android.R.layout.simple_spinner_item, yearList);
                dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                chooseyearviewSpinner.setAdapter(dataAdapter3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
