package com.example.frys.waters.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.frys.waters.controllers.ForgotPasswordActivity.userKey;
import static com.example.frys.waters.controllers.LoginActivity.currentUser;

public class NewPasswordActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String userEmail = userKey;


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
                final String newPasswords = newPassword.getText().toString();
                String confirmPasswords = confirmPassword.getText().toString();
                String currentUserEmail = databaseReference.child("user").child(userKey).child("email").toString();
                String currentUserPass = databaseReference.child("user").child(userKey).child("password").toString();
                if (newPasswords.compareTo(confirmPasswords) == 0) {
                    mAuth.sendPasswordResetEmail(userEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(NewPasswordActivity.this, "reset password email sent", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });



//                    AuthCredential credential = EmailAuthProvider.getCredential(currentUserEmail, currentUserPass);
//                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                user.updatePassword(newPasswords).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if(!task.isSuccessful()){
//                                            Toast.makeText(NewPasswordActivity.this, "new password and confirmation does not match 1", Toast.LENGTH_LONG).show();
//                                        }else {
//                                            Toast.makeText(NewPasswordActivity.this, "new password and confirmation does not match 2", Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                });
//                            }else {
//                                Toast.makeText(NewPasswordActivity.this, "new password and confirmation does not match 3", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
                } else {
                    Toast.makeText(NewPasswordActivity.this, "new password and confirmation does not match 4", Toast.LENGTH_LONG).show();
                }
//                if (newPasswords.compareTo(confirmPasswords) == 0) {
//                    if (newPasswords.length() < 6) {
//                        Toast.makeText(NewPasswordActivity.this, "Password is too short. Must be longer than 6", Toast.LENGTH_LONG).show();
//                    } else if (newPasswords.compareTo(databaseReference.child("user").child(userKey).child("password").toString()) == 0) {
//                        Toast.makeText(NewPasswordActivity.this, "The previous password matches the new password.", Toast.LENGTH_LONG).show();
//                    } else {
//                        databaseReference.child("user").child(userKey).child("password").setValue(newPassword.getText().toString());
//                    }
//                } else {
//                    Toast.makeText(NewPasswordActivity.this, "new password and confirmation does not match", Toast.LENGTH_LONG).show();
//                }
                startActivity(new Intent(NewPasswordActivity.this, WelcomeScreen.class));
            }
         });
    }
}
