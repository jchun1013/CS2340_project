package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.frys.waters.controllers.ForgotPasswordActivity.userKey;

public class NewPasswordActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);



        Button submitButton = (Button) findViewById(R.id.resetPasswordButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newPassword = (EditText) findViewById(R.id.newPasswordEditText);
                EditText confirmPassword = (EditText) findViewById(R.id.confirmPasswordEditText);
                String newPasswords = newPassword.getText().toString();
                String confirmPasswords = confirmPassword.getText().toString();
                if (newPasswords.compareTo(confirmPasswords) == 0) {
                    if (newPasswords.length() < 6) {
                        Toast.makeText(NewPasswordActivity.this, "Password is too short. Must be longer than 6", Toast.LENGTH_LONG).show();
                    } else if (newPasswords.compareTo(databaseReference.child("user").child(userKey).child("password").toString()) == 0) {
                        Toast.makeText(NewPasswordActivity.this, "The previous password matches the new password.", Toast.LENGTH_LONG).show();
                    } else {
                        databaseReference.child("user").child(userKey).child("password").setValue(newPassword.getText().toString());
                    }
                } else {
                    Toast.makeText(NewPasswordActivity.this, "new password and confirmation does not match", Toast.LENGTH_LONG).show();
                }
            }
         });
    }
}
