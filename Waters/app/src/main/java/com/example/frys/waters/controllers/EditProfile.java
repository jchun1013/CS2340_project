package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.LoginActivity.registeredUser;

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

        _name.setText(currentUser.getName());
        _email.setText(currentUser.getEmailAddress());
        _address.setText(currentUser.getHomeAddress());


        //user = (User) getIntent().getSerializableExtra("USER");

//        _username.setText(user.getUserName());
//        _name.setText(user.getName());
//        _email.setText(user.getEmail());
//        _address.setText(user.getAddress());

        _edit_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_name.getText().toString().equals(currentUser.getName())) {
                    currentUser.setName(_name.getText().toString());
                }
                if (!_email.getText().toString().equals(currentUser.getEmailAddress())) {
                    currentUser.setEmailAddress(_email.getText().toString());
                }
                if (!_address.getText().toString().equals(currentUser.getHomeAddress())) {
                    currentUser.setHomeAddress(_address.getText().toString());
                }
//                _name.setText(currentUser.getName());
//                _email.setText(currentUser.getEmailAddress());
//                _address.setText(currentUser.getHomeAddress());
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
