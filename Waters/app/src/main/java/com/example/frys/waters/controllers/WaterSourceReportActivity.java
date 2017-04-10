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
import com.example.frys.waters.model.WaterSourceReport;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

public class WaterSourceReportActivity extends AppCompatActivity {
    Spinner WaterConditionSpinner;
    Spinner waterTypeSpinner;
    static Location newLocation = new Location(0.0, 0.0);
    List<WaterSourceReport> lists;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String currentDateandTime = sdf.format(new Date());

    SourceReportDataBaseHandler db = new SourceReportDataBaseHandler(WaterSourceReportActivity.this);;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("source report");

    TextView numReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_source_report);

        numReport = (TextView) findViewById(R.id.numberOfReportTextView);
        TextView dateAndtime = (TextView) findViewById(R.id.localDateTimeActual);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.getChildrenCount();
                numReport.setText("" + (count + 1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //water type and water conditions array
        List<String> conditions = Arrays.asList("Waste", "Treatable-Clear", "Treatable-Muddy", "Portable");
        List<String> waterType = Arrays.asList("Bottled", "Well", "Stream", "Lake", "Spring", "Other");

        //buttons
        Button submitButton = (Button) findViewById(R.id.viewButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button enterLocationButton = (Button) findViewById(R.id.EnterLocationButton);

        WaterConditionSpinner = (Spinner) findViewById(R.id.spinner2);
        waterTypeSpinner = (Spinner) findViewById(R.id.typeOfWaterSpinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conditions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterConditionSpinner.setAdapter(dataAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, waterType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(typeAdapter);



        //numReport.setText("" + (db.countReport() + 1));
        dateAndtime.setText(currentDateandTime);

        enterLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setIsReporting(true);
                startActivity(new Intent(WaterSourceReportActivity.this, WaterAvailabilityActivity.class));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double lat = newLocation.getLatitude();
                double longi = newLocation.getLongitude();
                Location newLoc = new Location(lat, longi);
                if (newLoc.toString().equals("0.0,0.0")) {  // when location is not selected
                    Context context = getApplicationContext();
                    CharSequence text = "Location is required.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {    //when everything is selected
                    WaterSourceReport newReport = new WaterSourceReport(currentDateandTime, Integer.parseInt(numReport.getText().toString()), currentUser.getName(),
                            newLoc, (String) WaterConditionSpinner.getSelectedItem(), (String) waterTypeSpinner.getSelectedItem());
                    sourceReports.add(newReport);
                    db.addSourceReport(newReport);

                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(newReport);

                    startActivity(new Intent(WaterSourceReportActivity.this, RegUserActivity.class));
                    finish();
                }
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