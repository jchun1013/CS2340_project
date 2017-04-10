package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;
import static com.example.frys.waters.controllers.LoginActivity.registeredUser;

/**
 * This class allows user to edit his/her profile
 */
public class EditProfile extends AppCompatActivity {
    private Button _edit_change;
    private Button _edit_cancel;
    private TextView _username;
    private EditText _name, _email, _password, _confPassword, _address;
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
        _confPassword = (EditText) findViewById(R.id._confPassword);
        _address = (EditText) findViewById(R.id._address);

        _name.setText(currentUser.getName());
        _email.setText(currentUser.getEmailAddress());
        _address.setText(currentUser.getHomeAddress());

        _edit_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_confPassword.getText().toString().equals(_password.getText().toString())) {
                    if (!_name.getText().toString().equals(currentUser.getName())) {
                        currentUser.setName(_name.getText().toString());
                    }
                    if (!_email.getText().toString().equals(currentUser.getEmailAddress())) {
                        currentUser.setEmailAddress(_email.getText().toString());
                    }
                    if (!_address.getText().toString().equals(currentUser.getHomeAddress())) {
                        currentUser.setHomeAddress(_address.getText().toString());
                    }
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Your password and confirmation does not match.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
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
