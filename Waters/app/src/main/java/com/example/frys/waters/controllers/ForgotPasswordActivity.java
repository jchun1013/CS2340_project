package com.example.frys.waters.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.frys.waters.controllers.LoginActivity.currentUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    static String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button submitButton = (Button) findViewById(R.id.forgotPassword_enterEmail);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView enterEmail = (AutoCompleteTextView) findViewById(R.id.emailEnter);
                final String getEmail = enterEmail.getText().toString();

                databaseReference.child("user").orderByChild("emailAddress").equalTo(getEmail).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        userKey = null;

                        for (DataSnapshot child : children) {
                            userKey = child.getKey();
                        }
                        if (userKey == null) {
                            Context context = getApplicationContext();
                            CharSequence text = "The username you entered does not exist.";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            mAuth.sendPasswordResetEmail(getEmail)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ForgotPasswordActivity.this, "Reset email instructions sent to " + getEmail, Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(ForgotPasswordActivity.this, getEmail + " does not exist", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
