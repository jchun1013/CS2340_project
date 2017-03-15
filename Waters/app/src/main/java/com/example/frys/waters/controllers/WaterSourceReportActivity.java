package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.WaterSourceReport;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class WaterSourceReportActivity extends AppCompatActivity {
    Spinner WaterConditionSpinner;
    Spinner waterTypeSpinner;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public final String currentDateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_source_report);

        //water type and water conditions array
        List<String> conditions = Arrays.asList("Waste", "Treatable-Clear", "Treatable-Muddy", "Portable");
        List<String> waterType = Arrays.asList("Bottled", "Well", "Stream", "Lake", "Spring", "Other");

        //buttons
        Button submitButton = (Button) findViewById(R.id.viewButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button gMapButton = (Button) findViewById(R.id.gMap_Button);

        WaterConditionSpinner = (Spinner) findViewById(R.id.spinner2);
        waterTypeSpinner = (Spinner) findViewById(R.id.typeOfWaterSpinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conditions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterConditionSpinner.setAdapter(dataAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, waterType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(typeAdapter);


        TextView numReport = (TextView) findViewById(R.id.numberOfReportTextView);
        TextView dateAndtime = (TextView) findViewById(R.id.localDateTimeActual);
        numReport.setText("" + (sourceReports.size() + 1));
        dateAndtime.setText(currentDateandTime);

        gMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchWaterAvailabilityMap();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText locationEdit = (EditText) findViewById(R.id.locationTextView);
                if (locationEdit.getText().toString().equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Location is required.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    WaterSourceReport newReport = new WaterSourceReport(currentDateandTime, sourceReports.size() + 1, currentUser.getName(),
                            locationEdit.getText().toString(), (String) WaterConditionSpinner.getSelectedItem(), (String) waterTypeSpinner.getSelectedItem());
                    sourceReports.add(newReport);
                }
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void launchWaterAvailabilityMap() {
        Intent intent = new Intent(getApplicationContext(), WaterAvailabilityActivity.class);
        startActivity(intent);
    }
}
