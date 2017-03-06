package com.example.frys.waters.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frys.waters.R;

import java.util.ArrayList;
import java.util.List;

public class RegUserActivity extends AppCompatActivity {
    TextView _signOut;
    TextView _edit;
    Button _submitReport;
    static List<com.example.frys.waters.model.WaterSourceReport> sourceReports = new ArrayList();

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user_screen);
        _edit = (TextView)findViewById(R.id._edit);
        _signOut = (TextView)findViewById(R.id._signout);
        _submitReport = (Button) findViewById(R.id._submit);

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
        Intent intent = new Intent(getApplicationContext(), WaterSourceReport.class);
        startActivity(intent);
        //finish();
    }
}
