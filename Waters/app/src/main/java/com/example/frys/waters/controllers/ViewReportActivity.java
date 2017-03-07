package com.example.frys.waters.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.frys.waters.R;
import com.example.frys.waters.model.WaterSourceReport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class ViewReportActivity extends AppCompatActivity {
    Spinner viewSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        List<Integer> reports = new ArrayList<>();
        Iterator<WaterSourceReport> reportIterator = sourceReports.iterator();
        while (reportIterator.hasNext()) {
            reports.add(reportIterator.next().getReportNumber());
        }

        viewSpinner = (Spinner) findViewById(R.id._viewSpinner);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, reports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewSpinner.setAdapter(dataAdapter);
    }
}
