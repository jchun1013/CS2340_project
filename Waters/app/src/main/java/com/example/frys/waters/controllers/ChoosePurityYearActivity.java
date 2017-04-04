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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.example.frys.waters.controllers.ChoosePurityHistoryActivity.chooseLocationviewSpinner;

public class ChoosePurityYearActivity extends AppCompatActivity {

    Spinner chooseyearviewSpinner;

    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(ChoosePurityYearActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_purity_year);

        List<String> yearList = new ArrayList<>();
        Set<String> yearSet = new HashSet<>();

        String a = (String) chooseLocationviewSpinner.getSelectedItem();
        for (int i = 1; i < db.countReport() + 1; i++) {
            if (a.compareToIgnoreCase(getAddress(db.getLat(i), db.getLog(i))) == 0) {
                yearSet.add(db.getDateTime(i));
            }
        }
        yearList.addAll(yearSet);

        chooseyearviewSpinner = (Spinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseyearviewSpinner.setAdapter(dataAdapter3);

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

    public String getAddress(double lat, double log) {
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
}
