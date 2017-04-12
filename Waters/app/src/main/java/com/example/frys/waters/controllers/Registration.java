package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.example.frys.waters.controllers.LoginActivity.registeredUser;

/**
 * This class allows new user to register
 */
public class Registration extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private Spinner typeSpinner;

    //Commment out when junit testing
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference databaseReference = database.getInstance().getReference("user");
//    FirebaseAuth firebaseAuth;
    private boolean succ = true;

    EditText nameEdit;
    EditText emailEdit;
    EditText passwordEdit;
    EditText usernameEdit;
    EditText confirmEdit;
    EditText addressEdit;

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private static final String TAG = "EMAIL/PASSWORD";

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Comment out when junit testing
        //firebaseAuth = FirebaseAuth.getInstance();

        nameEdit = (EditText) findViewById(R.id.nameEditText);
        usernameEdit = (EditText) findViewById(R.id.usernameEditText);
        passwordEdit = (EditText) findViewById(R.id.passwordEditText);
        confirmEdit = (EditText) findViewById(R.id.confirmEditText);
        addressEdit = (EditText) findViewById(R.id.addressEditText);
        emailEdit = (EditText) findViewById(R.id.emailEditText);

        userTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (nameEdit.getText().toString().equals("")
                        || usernameEdit.getText().toString().equals("")
                        || confirmEdit.getText().toString().equals("")
                        || addressEdit.getText().toString().equals("")) {

                    Context context = getApplicationContext();
                    CharSequence text = "Need more registration information.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

                if (passwordEdit.getText().toString().equals(confirmEdit.getText().toString())) {
                    if (registeredUser.containsKey(usernameEdit.getText().toString())) {
                        Context context = getApplicationContext();
                        CharSequence text = "Username already exists.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }

                registerUser(emailEdit.getText().toString(), passwordEdit.getText().toString());

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

    public boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public void registerUser(String e, String p){

        //getting email and password from edit texts
        String email = e;
        String password = p;


        //checking if email and passwords are empty
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password is too short. Must be longer than 6", Toast.LENGTH_LONG). show();
        }


        //comment out when junit testing
        //creating a new user
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                        //checking if success
//                        if(task.isSuccessful()){
//                            UserType type = (UserType) userTypeSpinner.getSelectedItem();
//                                User newUser = new User(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
//                                        passwordEdit.getText().toString(), addressEdit.getText().toString());
//                                switch (type) {
//                                    case USER:
//                                        newUser = new User(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
//                                                passwordEdit.getText().toString(), addressEdit.getText().toString());
//                                        break;
//                                    case WORKER:
//                                        newUser = new Worker(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
//                                                passwordEdit.getText().toString(), addressEdit.getText().toString());
//                                        break;
//                                    case ADMIN:
//                                        newUser = new Admin(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
//                                                passwordEdit.getText().toString(), addressEdit.getText().toString());
//                                        break;
//                                    case MANAGER:
//                                        newUser = new Manager(usernameEdit.getText().toString(), nameEdit.getText().toString(), emailEdit.getText().toString(),
//                                                passwordEdit.getText().toString(), addressEdit.getText().toString());
//                                        break;
//                                }
//                            newUser.setUsertype((UserType) userTypeSpinner.getSelectedItem());
//
//                            //Commment out when junit testing
//                            //String id = databaseReference.push().getKey();
//                            //databaseReference.child(id).setValue(newUser);
//
//                            startActivity(new Intent(Registration.this, WelcomeScreen.class));
//
//                            Toast.makeText(Registration.this,"Successfully registered",Toast.LENGTH_LONG).show();
//                        }else{
//                            //display some message here
//                            Toast.makeText(Registration.this,"Email already exists",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
    }
}