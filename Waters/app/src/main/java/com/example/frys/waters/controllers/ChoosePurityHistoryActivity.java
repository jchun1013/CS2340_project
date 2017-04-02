package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.frys.waters.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoosePurityHistoryActivity extends AppCompatActivity {

    Spinner chooseLocationviewSpinner;
    Spinner choosePPMviewSpinner;
    Spinner chooseyearviewSpinner;
    PurityReportDataBaseHandler db = new PurityReportDataBaseHandler(ChoosePurityHistoryActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_purity_history);

        List<String> ppmType = Arrays.asList("Virus", "Contaminant");
        List<String> locationList = new ArrayList<>();
        for (int i = 0; i < db.countReport(); i++) {
            locationList.add(db.getLocation(i).toString());
        }
//        List<String> yearList = new ArrayList<>();
//        for (int i = 0; i < db.countReport(); i++) {
//            yearList.add(db.getDateTime(i).substring(0,4));
//        }

//        chooseLocationviewSpinner = (Spinner) findViewById(R.id.chooseLocationSpinner);
//        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, locationList);
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        choosePPMviewSpinner.setAdapter(dataAdapter1);


        choosePPMviewSpinner = (Spinner) findViewById(R.id.ppmTypeSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ppmType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosePPMviewSpinner.setAdapter(dataAdapter);

//        chooseyearviewSpinner = (Spinner) findViewById(R.id.yearSpinner);
//        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, yearList);
//        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        choosePPMviewSpinner.setAdapter(dataAdapter3);

        Button submit = (Button) findViewById(R.id.submitButton_choose);
        Button cancel = (Button) findViewById(R.id.cancelButton_choose);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosePurityHistoryActivity.this, HistoryGraphActivity.class));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
