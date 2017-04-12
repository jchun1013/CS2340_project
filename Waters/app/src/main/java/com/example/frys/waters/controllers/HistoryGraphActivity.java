package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.frys.waters.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import static com.example.frys.waters.controllers.ChoosePurityHistoryActivity.choosePPMviewSpinner;
import static com.example.frys.waters.controllers.ChoosePurityYearActivity.reportsToShow;

public class HistoryGraphActivity extends AppCompatActivity {
    PointsGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        double[] ppms = new double[12];
        int[] count = new int[12];
        String ppmType = (String) choosePPMviewSpinner.getSelectedItem();
        for (int i = 0; i < reportsToShow.size(); i++) {
            int month = Integer.parseInt(reportsToShow.get(i).getDateTime().substring(5,7));
            double ppm;
            if (ppmType.indexOf("Virus") == 0) {
                ppm = reportsToShow.get(i).getVirusPPM();
            } else {
                ppm = reportsToShow.get(i).getContaminantPPM();
            }
            count[month]++;
            ppms[month] = ppms[month] + ppm;
        }
        DataPoint[] list = new DataPoint[1];
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                list[j] = new DataPoint(i, ppms[i] / count[i]);
                j++;
            }
        }
        series = new PointsGraphSeries<>(list);

        graph.addSeries(series);
        graph.setTitle("Historical Purity Report");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("month");
        graph.getGridLabelRenderer().setVerticalAxisTitle("PPM");

        Button OKButton = (Button) findViewById(R.id.OKButton3);

        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HistoryGraphActivity.this, RegUserActivity.class));
            }
        });
    }
}