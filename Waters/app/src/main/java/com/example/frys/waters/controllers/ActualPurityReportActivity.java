package com.example.frys.waters.controllers;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frys.waters.R;
import com.example.frys.waters.model.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.frys.waters.controllers.RegUserActivity.purityReports;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;
import static com.example.frys.waters.controllers.ViewPurityReportActivity.selectedReport2;
import static com.example.frys.waters.controllers.ViewReportActivity.selectedReport;

public class ActualPurityReportActivity extends AppCompatActivity {
    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(ActualPurityReportActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_purity_report);

        TextView dateAndTime = (TextView) findViewById(R.id.actualDateAndTimeTextView2);
        TextView reporterNum = (TextView) findViewById(R.id.reporterNumberTextView2);
        TextView workerName = (TextView) findViewById(R.id.workerNameTextView);
        TextView waterLocation = (TextView) findViewById(R.id.LocationOfWaterTextView2);
        TextView overallConditionWater = (TextView) findViewById(R.id.overallConditionText);
        TextView virusPPM = (TextView) findViewById(R.id.virusPPMText);
        TextView contaminantPPM = (TextView) findViewById(R.id.contaminantPPMText);

        dateAndTime.setText(db.getDateTime(selectedReport2));
        reporterNum.setText("" + selectedReport2);
        workerName.setText(db.getNameOfWorker(selectedReport2));
        waterLocation.setText(getAddress());
        overallConditionWater.setText(db.getCondition(selectedReport2));
        virusPPM.setText("" + db.getVirusPPM(selectedReport2));
        contaminantPPM.setText("" + db.getConditionPPM(selectedReport2));

        Button okButton = (Button) findViewById(R.id.OKbutton2);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActualPurityReportActivity.this, RegUserActivity.class));
            }
        });
    }

    //move inside the database class?
    public String getAddress() {
        Geocoder gc = new Geocoder(ActualPurityReportActivity.this, Locale.getDefault());
        List<Address> addressList;
        Location loc = db.getLocation(selectedReport2);
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
                db.getLocation(selectedReport2).setFullAddress(returnAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnAddress;
    }
}
