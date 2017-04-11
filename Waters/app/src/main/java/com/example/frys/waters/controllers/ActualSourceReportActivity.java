package com.example.frys.waters.controllers;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frys.waters.R;
import com.example.frys.waters.model.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;
import static com.example.frys.waters.controllers.ViewReportActivity.selectedReport;
import static com.example.frys.waters.controllers.ViewReportActivity.selectedReportObject;
import static com.example.frys.waters.controllers.ViewReportActivity.viewSpinner;

/**
 * This class displays each of water source report information
 */
public class ActualSourceReportActivity extends AppCompatActivity {
    /**
     * Displays water source report information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_source_report);

        TextView dateAndTime = (TextView) findViewById(R.id.actualDateAndTimeTextView);
        TextView reporterNum = (TextView) findViewById(R.id.reporterNumberTextView);
        TextView reporterName = (TextView) findViewById(R.id.reporterNameTextView);
        TextView waterLocation = (TextView) findViewById(R.id.LocationOfWaterTextView);
        TextView typeOfWater = (TextView) findViewById(R.id.typeOfWaterTextView);
        TextView conditionWater = (TextView) findViewById(R.id.conditionOfWaterTextView);

        dateAndTime.setText(selectedReportObject.getDateTime());
        reporterNum.setText("" + selectedReport);
        reporterName.setText(selectedReportObject.getNameOfReporter());
        waterLocation.setText(getAddress(selectedReportObject.getLocation()));
        typeOfWater.setText(selectedReportObject.getTypeOfWater());
        conditionWater.setText(selectedReportObject.getCondition());

        Button okButton = (Button) findViewById(R.id.OKbutton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActualSourceReportActivity.this, RegUserActivity.class));
            }
        });

    }

    public String getAddress(Location location) {
        Geocoder gc = new Geocoder(ActualSourceReportActivity.this, Locale.getDefault());
        List<Address> addressList;
        Location loc = location;
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
                //db.getLocation(selectedReport).setFullAddress(returnAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnAddress;
    }
}
