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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPurityReportActivity extends AppCompatActivity {

    private static Spinner viewSpinner2;
    public static int selectedReport2;
    private DatabaseReference databaseReference;
    private List<Integer> reports = new ArrayList<>();
    private Map<Integer, WaterPurityReport> reportMap = new HashMap<>();
    public static WaterPurityReport selectedReportObject2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purity_report);

        List<Integer> reports = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        getAllReports();

        Button backButton = (Button) findViewById(R.id.BackButton2);
        Button viewButton = (Button) findViewById(R.id.viewButton2);

        viewSpinner2 = (Spinner) findViewById(R.id._viewSpinner2);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, reports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewSpinner2.setAdapter(dataAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedReport2 = (int) viewSpinner2.getSelectedItem();
                selectedReportObject2 = reportMap.get(selectedReport2);
                startActivity(new Intent(ViewPurityReportActivity.this, ActualPurityReportActivity.class));
            }
        });
    }

    private void getAllReports() {
        databaseReference.child("purity report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    WaterPurityReport childValue = child.getValue(WaterPurityReport.class);
                    childValue.setLocation(Double.parseDouble(child.child("location").child("latitude").getValue().toString())
                            , Double.parseDouble(child.child("location").child("longitude").getValue().toString()));
                    reportMap.put(childValue.getReportNumber(), childValue);
                    reports.add(childValue.getReportNumber());
                }

                viewSpinner2 = (Spinner) findViewById(R.id._viewSpinner2);
                ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(ViewPurityReportActivity.this, android.R.layout.simple_spinner_item, reports);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                viewSpinner2.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
