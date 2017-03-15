package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frys.waters.R;

import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;
import static com.example.frys.waters.controllers.ViewReportActivity.selectedReport;
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

        dateAndTime.setText(sourceReports.get(selectedReport - 1).getDateTime());
        reporterNum.setText("" + sourceReports.get(selectedReport - 1).getReportNumber());
        reporterName.setText(sourceReports.get(selectedReport - 1).getNameOfReporter());
        waterLocation.setText(sourceReports.get(selectedReport - 1).getLocation().toString());
        typeOfWater.setText(sourceReports.get(selectedReport - 1).getTypeOfWater());
        conditionWater.setText(sourceReports.get(selectedReport - 1).getCondition());

        Button okButton = (Button) findViewById(R.id.OKbutton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActualSourceReportActivity.this, RegUserActivity.class));
            }
        });

    }
}
