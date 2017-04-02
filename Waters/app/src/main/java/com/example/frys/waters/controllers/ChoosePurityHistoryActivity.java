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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.frys.waters.controllers.ViewPurityReportActivity.selectedReport2;

public class ChoosePurityHistoryActivity extends AppCompatActivity {

    static Spinner chooseLocationviewSpinner;
    Spinner choosePPMviewSpinner;
    Spinner chooseyearviewSpinner;
    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(ChoosePurityHistoryActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_purity_history);

        List<String> ppmType = Arrays.asList("Virus", "Contaminant");
        List<String> yearList = new ArrayList<>();
        List<String> locationList = new ArrayList<>();
        for (int i = 1; i < db.countReport() + 1; i++) {
            locationList.add(getAddress(i));
            //locationList.add(db.getLocation(i).toString());
        }

//        List<String> yearList = new ArrayList<>();
//        for (int i = 1; i < db.countReport() + 1; i++) {
//            yearList.add(db.getDateTime(i).substring(0,4));
//        }

        chooseLocationviewSpinner = (Spinner) findViewById(R.id.chooseLocationSpinner);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, locationList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseLocationviewSpinner.setAdapter(dataAdapter1);

        String a = (String) chooseLocationviewSpinner.getSelectedItem();
        String[] years = db.getAllYear(a);
        for (int i = 0; i < years.length; i++) {
            yearList.add(years[i]);
        }

        choosePPMviewSpinner = (Spinner) findViewById(R.id.ppmTypeSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ppmType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePPMviewSpinner.setAdapter(dataAdapter);

        chooseyearviewSpinner = (Spinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseyearviewSpinner.setAdapter(dataAdapter3);

        Button submit = (Button) findViewById(R.id.submitButton_choose);
        Button cancel = (Button) findViewById(R.id.cancelButton_choose);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosePurityHistoryActivity.this, HistoryGraphActivity.class));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public String getAddress(int report) {
        Geocoder gc = new Geocoder(ChoosePurityHistoryActivity.this, Locale.getDefault());
        List<Address> addressList;
        Location loc = db.getLocation(report);
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
}
