package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    static String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        AutoCompleteTextView enterEmail = (AutoCompleteTextView) findViewById(R.id.emailEnter);
        String getEmail = enterEmail.getText().toString();
        System.out.println(getEmail);

        Button submitButton = (Button) findViewById(R.id.forgotPassword_enterEmail);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    public void changePassword() {
        AutoCompleteTextView enterEmail = (AutoCompleteTextView) findViewById(R.id.emailEnter);
        String getEmail = enterEmail.getText().toString();
        databaseReference.child("user").orderByChild("emailAddress").equalTo(getEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                setKey(null);
                //String databaseKey = null;

                for (DataSnapshot child : children) {
                    setKey(child.getKey());
                }
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setKey(String key) {
        this.userKey = key;
    }
}
