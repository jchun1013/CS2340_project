package com.example.frys.waters.controllers;

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

import com.example.frys.waters.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class WaterSourceReport extends AppCompatActivity {
    Spinner WaterConditionSpinner;
    Button submit;
    Button cancel;

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

        submit = (Button) findViewById(R.id.submitButton);
        cancel = (Button) findViewById(R.id.cancelButton);

        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
