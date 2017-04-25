package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;
import com.example.frys.waters.model.WaterSourceReport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class WaterSourceBarGraphActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    WaterSourceReport childvalue;
    int[] count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_source_bar_graph);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();

        BarGraphSeries<DataPoint> series;

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        count = new int[6];
        //count[4] = 3;

        databaseReference.child("source report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    childvalue = child.getValue(WaterSourceReport.class);

                    if (childvalue.getTypeOfWater().toString().compareToIgnoreCase("Bottled") == 0) {
                        count[0] = count[0] + 1;
                    } else if (childvalue.getTypeOfWater().toString().compareToIgnoreCase("Well") == 0) {
                        count[1] = count[1] + 1;
                    } else if (childvalue.getTypeOfWater().toString().compareToIgnoreCase("stream") == 0) {
                        count[2] = count[2] + 1;
                    } else if (childvalue.getTypeOfWater().toString().compareToIgnoreCase("lake") == 0) {
                        count[3] = count[3] + 1;
                    } else if (childvalue.getTypeOfWater().toString().compareToIgnoreCase("spring") == 0) {
                        count[4] = count[4] + 1;
                    } else {
                        count[5] = count[5] + 1;
                    }

                }
//                System.out.println(childvalue.getTypeOfWater().toString())

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        DataPoint[] list = new DataPoint[6];
        for (int i = 0; i < 6; i++) {
            list[i] = new DataPoint(i, count[i]);
        }
        series = new BarGraphSeries<>(list);
        graph.addSeries(series);

        graph.setTitle("Historical Source Report");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Type of Water");
        graph.getGridLabelRenderer().setVerticalAxisTitle("count");

        Button OKButton = (Button) findViewById(R.id.OKButton4);

        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaterSourceBarGraphActivity.this, RegUserActivity.class));
            }
        });
    }

    private void getCount() {
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    childvalue = child.getValue(WaterSourceReport.class);
                    if (childvalue.getTypeOfWater().equalsIgnoreCase("bottled")) {
                        count[0]++;
                    } else if (childvalue.getTypeOfWater().equalsIgnoreCase("well")) {
                        count[1]++;
                    } else if (childvalue.getTypeOfWater().equalsIgnoreCase("stream")) {
                        count[2]++;
                    } else if (childvalue.getTypeOfWater().equalsIgnoreCase("lake")) {
                        count[3]++;
                    } else if (childvalue.getTypeOfWater().equalsIgnoreCase("spring")) {
                        count[4]++;
                    } else {
                        count[5]++;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
