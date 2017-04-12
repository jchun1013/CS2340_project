package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.UserType;
import com.example.frys.waters.model.WaterPurityReport;
import com.example.frys.waters.model.WaterSourceReport;

import java.util.ArrayList;
import java.util.List;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;

public class RegUserActivity extends AppCompatActivity {
    private TextView _signOut;
    private TextView _edit;
    private TextView _userInfo;
    private Button _submitReport;
    private Button _viewReport;
    private Button _waterAvailabilityMap;
    private Button _waterQualityReport;
    private Button _viewPurityReport;
    private Button _historyGraph;
    static List<WaterSourceReport> sourceReports = new ArrayList();
    static List<WaterPurityReport> purityReports = new ArrayList();

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sourceReports.add(new WaterSourceReport(1, Joon, ))
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user_screen);
        _edit = (TextView)findViewById(R.id._edit);
        _signOut = (TextView)findViewById(R.id._signout);
        _submitReport = (Button) findViewById(R.id._submit);
        _viewReport = (Button) findViewById(R.id._view);
        _waterAvailabilityMap = (Button) findViewById(R.id.waterAvailabilityMapButton);
        _waterQualityReport = (Button) findViewById(R.id.waterQualityReportButton);
        _viewPurityReport = (Button) findViewById(R.id.viewWaterQualityReportButton);
        _historyGraph = (Button) findViewById(R.id.historyGraphButton);

        _userInfo = (TextView) findViewById(R.id._hello);
        _userInfo.setText(currentUser.getName() + " (" + currentUser.getUsertype() + ")");

        _signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignOut();
            }
        });
        _edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEdit();
            }
        });
        _submitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSubmitReport();
            }
        });
        _viewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchViewReport();
            }
        });
        _waterAvailabilityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWaterAvailabilityMap();
            }
        });

        _historyGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHistoryGraph();
            }
        });

        _waterQualityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.getUsertype().equals(UserType.MANAGER) || currentUser.getUsertype().equals(UserType.WORKER)) {
                    startActivity(new Intent(RegUserActivity.this, WaterPurityReportActivity.class));
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Only Manager or Worker has access to water quality report.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        _viewPurityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.getUsertype() == UserType.MANAGER) {
                    startActivity(new Intent(RegUserActivity.this, ViewPurityReportActivity.class));
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Only Manager has access to water quality report.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    /**
     * Launches Main Activity after sign out
     */
    private void launchSignOut() {
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(intent);
        finish();
    }

    /**
     * Lauches Edit Profile Activity
     */
    private void launchEdit() {
        Intent intent = new Intent(getApplicationContext(), EditProfile.class);
        startActivity(intent);
    }

    private void launchSubmitReport() {
        Intent intent = new Intent(getApplicationContext(), WaterSourceReportActivity.class);
        startActivity(intent);
        //finish();
    }

    private void launchViewReport() {
        Intent intent = new Intent(getApplicationContext(), ViewReportActivity.class);
        startActivity(intent);
    }

    private void launchWaterAvailabilityMap() {
        Intent intent = new Intent(getApplicationContext(), WaterAvailabilityActivity.class);
        startActivity(intent);
    }

    private void launchHistoryGraph() {
        Intent intent = new Intent(getApplicationContext(), ChoosePurityHistoryActivity.class);
        startActivity(intent);
    }
}