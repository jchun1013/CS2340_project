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
import com.example.frys.waters.model.Location;
import com.example.frys.waters.model.WaterPurityReport;
import com.example.frys.waters.model.WaterSourceReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.RegUserActivity.purityReports;

/**
 * Water Purity Report
 */
public class WaterPurityReportActivity extends AppCompatActivity {

    Spinner overallConditionSpinner;
    static Location newPurityLocation = new Location(0.0, 0.0);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String currentDateandTime = sdf.format(new Date());
    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(WaterPurityReportActivity.this);
    boolean addCount = true;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("purity report");
    TextView numReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_purity_report);

        List<String> conditions = Arrays.asList("Safe", "Treatable", "Unsafe");
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton2);
        Button enterLocationButton2 = (Button) findViewById(R.id.locationButton2);

        overallConditionSpinner = (Spinner) findViewById(R.id._overallConditionSpinner);
        numReport = (TextView) findViewById(R.id._reportNumberText);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conditions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overallConditionSpinner.setAdapter(dataAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (addCount) {
                    int count = (int) dataSnapshot.getChildrenCount();
                    numReport.setText("" + (count + 1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TextView dateAndtime = (TextView) findViewById(R.id._dateAndTimeText);
        dateAndtime.setText(currentDateandTime);
        TextView workerName = (TextView) findViewById(R.id._workerNameEdit);
        workerName.setText(currentUser.getName());

        EditText virusPPM = (EditText) findViewById(R.id._virusPPMEdit);
        EditText conditionPPM = (EditText) findViewById(R.id._contaminantPPMEdit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView locationText = (TextView) findViewById(R.id._locationEdit);
                EditText virusPPM = (EditText) findViewById(R.id._virusPPMEdit);
                EditText contaminantPPM = (EditText) findViewById(R.id._contaminantPPMEdit);

                double lat = newPurityLocation.getLatitude();
                double longi = newPurityLocation.getLongitude();
                Location newLoc = new Location(lat, longi);

                //locationText.getText().toString().equals("") ||
                if (virusPPM.getText().toString().equals("") || contaminantPPM.getText().toString().equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "One of the field is missing: location, virus (PPM), contaminant (PPM)";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    startActivity(new Intent(WaterPurityReportActivity.this, WaterPurityReportActivity.class));
                } else {
                    WaterPurityReport newReport = new WaterPurityReport(currentDateandTime, Integer.parseInt(numReport.getText().toString()), currentUser.getName(),
                            newLoc, (String) overallConditionSpinner.getSelectedItem(), Double.parseDouble(virusPPM.getText().toString())
                            , Double.parseDouble(contaminantPPM.getText().toString()));
//                    purityReports.add(newReport);
//                    db.addPurityReport(newReport);

                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(newReport);
                    addCount = false;

                    startActivity(new Intent(WaterPurityReportActivity.this, RegUserActivity.class));
                    finish();
                }
            }
        });

        enterLocationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setIsReporting(true);
                startActivity(new Intent(WaterPurityReportActivity.this, PurityReportMap.class));
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
