package com.example.frys.waters.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;

public class EditProfile extends AppCompatActivity {
    private Button _edit_change;
    private Button _edit_cancel;
    private TextView _username;
    private EditText _name, _email, _password, _address;
    private User user;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        _edit_change = (Button) findViewById(R.id._edit_change);
        _edit_cancel = (Button) findViewById(R.id._edit_cancel);

        _username = (TextView) findViewById(R.id._username);
        _name = (EditText) findViewById(R.id._name);
        _email = (EditText) findViewById(R.id._email);
        _password = (EditText) findViewById(R.id._password);
        _address = (EditText) findViewById(R.id._address);

        //user = (User) getIntent().getSerializableExtra("USER");

//        _username.setText(user.getUserName());
//        _name.setText(user.getName());
//        _email.setText(user.getEmail());
//        _address.setText(user.getAddress());

        _edit_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _edit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
