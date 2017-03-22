package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frys.waters.R;

import static com.example.frys.waters.controllers.RegUserActivity.purityReports;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;
import static com.example.frys.waters.controllers.ViewPurityReportActivity.selectedReport2;
import static com.example.frys.waters.controllers.ViewReportActivity.selectedReport;

public class ActualPurityReportActivity extends AppCompatActivity {

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

        dateAndTime.setText(purityReports.get(selectedReport2 - 1).getDateTime());
        reporterNum.setText("" + purityReports.get(selectedReport2 - 1).getReportNumber());
        workerName.setText(purityReports.get(selectedReport2 - 1).getNameOfWorker());
        waterLocation.setText(purityReports.get(selectedReport2 - 1).getLocation().toString());
        overallConditionWater.setText(purityReports.get(selectedReport2 - 1).getCondition());
        virusPPM.setText("" + purityReports.get(selectedReport2 - 1).getVirusPPM());
        contaminantPPM.setText("" + purityReports.get(selectedReport2 - 1).getContaminantPPM());

        Button okButton = (Button) findViewById(R.id.OKbutton2);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActualPurityReportActivity.this, RegUserActivity.class));
            }
        });
    }
}
