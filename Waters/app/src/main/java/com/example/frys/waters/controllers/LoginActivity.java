package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    //public static final Map<String, User> registeredUser = new HashMap();
    public static final Map<java.lang.String,com.example.frys.waters.model.User> registeredUser = new HashMap<>();
    public static User currentUser;
    public static String currentUserKey;
    public static String c;
    User childValue;
    User childValue2;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private EditText editEmail;
    private EditText editPassword;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    static Map<String, Integer> emailList = new HashMap<>();
    static boolean banned;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //registeredUser.put("user", new User("test", "test", "test", "pass", "test"));

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        TextView textForgotPassword = (TextView) findViewById(R.id.forgotPassword);

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
        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    private void attemptLogin() {
        final String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        setCurrentUser();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //if (childValue.getBanned() == false) {
                    if (task.isSuccessful()) {
                        if (childValue.getEmailAddress().equals(editEmail.getText().toString())) {
                            currentUser = childValue;
                        }
                        startActivity(new Intent(LoginActivity.this, RegUserActivity.class));
                        finish();
                    } else {
//                        if (childValue.getEmailAddress().equals(editEmail.getText().toString())) {
//                            emailList(childValue.getEmailAddress());
//                        }
                        Toast.makeText(LoginActivity.this, "Login Attempt Failed", Toast.LENGTH_LONG).show();
                        return;
                    }
//                } else {
//                    Toast.makeText(LoginActivity.this, "You have been banned!", Toast.LENGTH_LONG).show();
//                    return;
//                }
            }
        });
    }


    private void setCurrentUser() {
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    childValue = child.getValue(User.class);
                    currentUserKey = child.getKey();


//                    if (childValue.getEmailAddress().equals(editEmail.getText().toString())) {
//                        currentUser = childValue;
//                        currentUserKey = child.toString();
//                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void emailList(String email) {
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    childValue2 = child.getValue(User.class);
                    if (!emailList.containsKey(childValue2.getEmailAddress())) {
                        emailList.put(childValue2.getEmailAddress(), 0);
                    } else {
                        int count = emailList.get(childValue2.getEmailAddress()) + 1;
                        if (count >= 4) {
                            databaseReference.child("user").child(child.getKey()).child("banned").setValue(true);
                            //Toast.makeText(LoginActivity.this, "5 login attempts Failed. Banned", Toast.LENGTH_SHORT).show();
                            //return;
                        } else {
                            emailList.put(childValue2.getEmailAddress(), count);
                            //Toast.makeText(LoginActivity.this, "Login Attempt Failed", Toast.LENGTH_SHORT).show();
                            //return;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}