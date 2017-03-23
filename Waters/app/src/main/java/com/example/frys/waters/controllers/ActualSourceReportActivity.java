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
    SourceReportDataBaseHandler db = new SourceReportDataBaseHandler(ActualSourceReportActivity.this, null, null, 1);
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

        dateAndTime.setText(db.getDateTime(selectedReport - 1));
        reporterNum.setText("" + selectedReport);
        reporterName.setText(db.getReporterName(selectedReport - 1));
        waterLocation.setText(db.getLocation(selectedReport - 1));
        typeOfWater.setText(db.getWaterType(selectedReport - 1));
        conditionWater.setText(db.getCondition(selectedReport - 1));

        Button okButton = (Button) findViewById(R.id.OKbutton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActualSourceReportActivity.this, RegUserActivity.class));
            }
        });

    }
}
