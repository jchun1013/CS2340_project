package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frys.waters.R;
import com.example.frys.waters.model.WaterSourceReport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class ViewReportActivity extends AppCompatActivity {
    public static Spinner viewSpinner;
    public static int selectedReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        List<Integer> reports = new ArrayList<>();
        Iterator<WaterSourceReport> reportIterator = sourceReports.iterator();
        while (reportIterator.hasNext()) {
            reports.add(reportIterator.next().getReportNumber());
        }

        Button backButton = (Button) findViewById(R.id.BackButton);
        Button viewButton = (Button) findViewById(R.id.viewButton);

        viewSpinner = (Spinner) findViewById(R.id._viewSpinner);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, reports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewSpinner.setAdapter(dataAdapter);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(WelcomeScreen.this, LoginActivity.class));
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedReport = (int) viewSpinner.getSelectedItem();
                startActivity(new Intent(ViewReportActivity.this, ActualSourceReportActivity.class));
            }
        });


    }
}
