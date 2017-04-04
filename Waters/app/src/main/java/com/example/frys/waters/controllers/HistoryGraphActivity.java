package com.example.frys.waters.controllers;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.frys.waters.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HistoryGraphActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph);

        double x,y;
        x = 0;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 10; i++) {
            x = x + 1;
            y = x * x;
            series.appendData(new DataPoint(x, y), true, 10);
        }

        graph.addSeries(series);
        graph.setTitle("Historical Purity Report");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("month");
        graph.getGridLabelRenderer().setVerticalAxisTitle("PPM");
    }
}