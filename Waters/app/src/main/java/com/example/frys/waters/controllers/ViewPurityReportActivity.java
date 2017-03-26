package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.frys.waters.R;
import com.example.frys.waters.model.WaterPurityReport;
import com.example.frys.waters.model.WaterSourceReport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.frys.waters.controllers.RegUserActivity.purityReports;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class ViewPurityReportActivity extends AppCompatActivity {

    public static Spinner viewSpinner2;
    public static int selectedReport2;
    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(ViewPurityReportActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purity_report);

        List<Integer> reports = new ArrayList<>();

        int[] reportNums = db.getAllReportNum();
        for (int i = 0; i < reportNums.length; i++) {
            reports.add(reportNums[i]);
        }

        Button backButton = (Button) findViewById(R.id.BackButton2);
        Button viewButton = (Button) findViewById(R.id.viewButton2);

        viewSpinner2 = (Spinner) findViewById(R.id._viewSpinner2);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, reports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewSpinner2.setAdapter(dataAdapter);



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
                selectedReport2 = (int) viewSpinner2.getSelectedItem();
                startActivity(new Intent(ViewPurityReportActivity.this, ActualPurityReportActivity.class));
            }
        });
    }
}
