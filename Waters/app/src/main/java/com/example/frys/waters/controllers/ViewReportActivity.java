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
import com.example.frys.waters.model.User;
import com.example.frys.waters.model.WaterPurityReport;
import com.example.frys.waters.model.WaterSourceReport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.RegUserActivity.sourceReports;

/**
 * This class allows user to view reports that have been submitted
 */
public class ViewReportActivity extends AppCompatActivity {
    public static Spinner viewSpinner;
    public static int selectedReport;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Integer> reports = new ArrayList<>();
    private Map<Integer, WaterSourceReport> reportMap = new HashMap<>();
    public static WaterSourceReport selectedReportObject;
    List<DataSnapshot> lists = new ArrayList<>();

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        getAllReports();

        Button backButton = (Button) findViewById(R.id.BackButton);
        Button viewButton = (Button) findViewById(R.id.viewButton);


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
                selectedReportObject = reportMap.get(selectedReport);
                startActivity(new Intent(ViewReportActivity.this, ActualSourceReportActivity.class));
            }
        });
    }

    private void getAllReports() {
        databaseReference.child("source report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    WaterSourceReport childValue = child.getValue(WaterSourceReport.class);
                    childValue.setTypeOfWater(child.child("typeOfWater").getValue().toString());
                    childValue.setLocation(Double.parseDouble(child.child("location").child("latitude").getValue().toString())
                        , Double.parseDouble(child.child("location").child("longitude").getValue().toString()));

                    reportMap.put(childValue.getReportNumber(), childValue);
                    reports.add(childValue.getReportNumber());
                }

                viewSpinner = (Spinner) findViewById(R.id._viewSpinner);
                ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(ViewReportActivity.this, android.R.layout.simple_spinner_item, reports);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                viewSpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
