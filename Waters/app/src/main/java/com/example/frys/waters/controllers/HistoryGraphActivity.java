package com.example.frys.waters.controllers;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.frys.waters.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.frys.waters.controllers.ChoosePurityHistoryActivity.choosePPMviewSpinner;
import static com.example.frys.waters.controllers.ChoosePurityYearActivity.reportsToShow;

public class HistoryGraphActivity extends AppCompatActivity {
    PointsGraphSeries<DataPoint> series;
    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(HistoryGraphActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph);

        double x,y;
        x = 0;
        y = 0;

        GraphView graph = (GraphView) findViewById(R.id.graph);

        int[] ppms = new int[12];
        int[] count = new int[12];
        //series = new LineGraphSeries<DataPoint>();
        String ppmType = (String) choosePPMviewSpinner.getSelectedItem();
        for (int i = 0; i < reportsToShow.size(); i++) {
            int reportNumber = reportsToShow.get(i);
            int month = Integer.parseInt(db.getDateTime(reportNumber).substring(5,7));
            double ppm;
            if (ppmType.indexOf("Virus") == 0) {
                ppm = (int) db.getVirusPPM(reportNumber);
            } else {
                ppm = (int) db.getConditionPPM(reportNumber);
            }
            count[month]++;
            ppms[month] += ppm;
            //list[i] = new DataPoint(month, ppm / count[month]);
        }
        DataPoint[] list = new DataPoint[1];
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                list[j] = new DataPoint(i, (double)ppms[i] / count[i]);
                System.out.println("HIIIIIIIIIIIIIIIIIIIII" +  ppms[i] + "    " + count[i]);
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