package com.example.frys.waters.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    public static Map<String, User> registeredUser = new HashMap();
    public static User currentUser;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private EditText username;
    private EditText password;
    RegistrationDataBaseHandler db;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registeredUser.put("user", new User("test", "test", "test", "pass", "test"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);


        Button SignInButton = (Button) findViewById(R.id.email_sign_in_button);
        SignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        // After pressing cancel button, go back to welcome screen.
        Button cancelButton = (Button) findViewById(R.id.button3);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, WelcomeScreen.class));
            }
        });
    }

    public void attemptLogin() {
        db = new RegistrationDataBaseHandler(LoginActivity.this);

        //checks if username exists in the database
        if (db.usernameExist(username.getText().toString())) {
            String passwordInDB = db.getPassword(username.getText().toString());
            //if username exists, then compare the password
            if (password.getText().toString().equals(passwordInDB)) {
                currentUser = db.getUser(username.getText().toString());
                currentUser.setIsReporting(false);
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Bad login attempt.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else {
            //case when username doesn't exist in the database
            Context context = getApplicationContext();
            CharSequence text = "Bad login attempt. Username doesn't exist.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
