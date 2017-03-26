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

/**
 * This class allows user to view reports that have been submitted
 */
public class ViewReportActivity extends AppCompatActivity {
    public static Spinner viewSpinner;
    public static int selectedReport;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SourceReportDataBaseHandler db = new SourceReportDataBaseHandler(ViewReportActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        List<Integer> reports = new ArrayList<>();

        int[] reportNums = db.getAllReportNum();
        for (int i = 0; i < reportNums.length; i++) {
            reports.add(reportNums[i]);
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
