package com.example.frys.waters.controllers;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frys.waters.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class WaterSourceReport extends AppCompatActivity {
    Spinner WaterConditionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_source_report);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        List<String> conditions = Arrays.asList("Waste", "Treatable-Clear", "Treatable-Muddy", "Portable");

        WaterConditionSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conditions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterConditionSpinner.setAdapter(dataAdapter);

        TextView numReport = (TextView) findViewById(R.id.numberOfReportTextView);
        numReport.setText("" + sourceReports.size());
    }
}
