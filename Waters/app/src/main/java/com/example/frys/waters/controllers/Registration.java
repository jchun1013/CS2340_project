package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.Admin;
import com.example.frys.waters.model.Manager;
import com.example.frys.waters.model.User;
import com.example.frys.waters.model.UserType;
import com.example.frys.waters.model.Worker;

import java.util.HashMap;
import java.util.Map;

import static com.example.frys.waters.controllers.LoginActivity.registeredUser;

/**
 * This class allows new user to register
 */
public class Registration extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private Spinner typeSpinner;
    RegistrationDataBaseHandler registrationDataBase;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEdit = (EditText) findViewById(R.id.nameEditText);
                EditText usernameEdit = (EditText) findViewById(R.id.usernameEditText);
                EditText passwordEdit = (EditText) findViewById(R.id.passwordEditText);
                EditText confirmEdit = (EditText) findViewById(R.id.confirmEditText);
                EditText addressEdit = (EditText) findViewById(R.id.addressEditText);
                EditText emailEdit = (EditText) findViewById(R.id.emailEditText);


                if (nameEdit.getText().toString().equals("") ||
                        usernameEdit.getText().toString().equals("") ||
                        passwordEdit.getText().toString().equals("") ||
                        confirmEdit.getText().toString().equals("") ||
                        addressEdit.getText().toString().equals("") ||
                        emailEdit.getText().toString().equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Need more registration information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    if (passwordEdit.getText().toString().equals(confirmEdit.getText().toString())) {
                        if (registeredUser.containsKey(usernameEdit.getText().toString())) {
                            Context context = getApplicationContext();
                            CharSequence text = "Username already exists.";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            UserType type = (UserType) userTypeSpinner.getSelectedItem();
                            User newUser = new User(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
                                    passwordEdit.getText().toString(), addressEdit.getText().toString());
                            switch(type) {
                                case USER: newUser = new User(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
                                        passwordEdit.getText().toString(), addressEdit.getText().toString());
                                    break;
                                case WORKER: newUser = new Worker(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
                                        passwordEdit.getText().toString(), addressEdit.getText().toString());
                                    break;
                                case ADMIN: newUser = new Admin(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
                                        passwordEdit.getText().toString(), addressEdit.getText().toString());
                                    break;
                                case MANAGER: newUser = new Manager(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
                                        passwordEdit.getText().toString(), addressEdit.getText().toString());
                                    break;

                            }
                            newUser.setUsertype((UserType) userTypeSpinner.getSelectedItem());
                            registeredUser.put(usernameEdit.getText().toString(), newUser);
                            registrationDataBase = new RegistrationDataBaseHandler(Registration.this, null, null, 2);
                            registrationDataBase.addRegister(newUser);

                            startActivity(new Intent(Registration.this, WelcomeScreen.class));
                        }
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Password and confirm password does not match.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.RegisterCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, WelcomeScreen.class));
            }
        });
    }
}
