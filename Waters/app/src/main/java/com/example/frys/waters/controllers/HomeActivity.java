package com.example.frys.waters.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frys.waters.R;

public class HomeActivity extends AppCompatActivity {
    TextView _signOut;
    TextView _edit;
    Button _submitReport;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
//        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
//        startActivity(intent);
    }

    private void launchSubmitReport() {
//        Intent intent = new Intent(getApplicationContext(), WaterReportActivity.class);
//        startActivity(intent);
//        finish();
    }
}
