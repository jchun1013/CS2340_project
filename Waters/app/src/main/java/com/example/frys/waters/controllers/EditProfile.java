package com.example.frys.waters.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frys.waters.R;
import com.example.frys.waters.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.FirebaseDatabase;
import static com.example.frys.waters.controllers.LoginActivity.currentUser;

/**
 * This class allows user to edit his/her profile
 */
public class EditProfile extends AppCompatActivity {

    private EditText _name, _email, _password, _confPassword, _address;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Button _edit_change = (Button) findViewById(R.id._edit_change);
        Button _edit_cancel = (Button) findViewById(R.id._edit_cancel);

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
                changeProfile();
//                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//                DatabaseReference child = ref.child("Users");
//
//                if (_confPassword.getText().toString().equals(_password.getText().toString())) {
//                    if (!_name.getText().toString().equals(currentUser.getName())) {
//
//                        //databaseReference.child("user").child("name").setValue(_name.getText().toString());
//                        currentUser.setName(_name.getText().toString());
//                    }
//                    if (!_email.getText().toString().equals(currentUser.getEmailAddress())) {
//                        Toast.makeText(EditProfile.this,"Email cannot be changed",Toast.LENGTH_LONG).show();
//                        //currentUser.setEmailAddress(_email.getText().toString());
//                    }
//                    if (!_address.getText().toString().equals(currentUser.getHomeAddress())) {
//                        currentUser.setHomeAddress(_address.getText().toString());
//                    }
//                } else {
//                    Context context = getApplicationContext();
//                    CharSequence text = "Your password and confirmation does not match.";
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                }
//                finish();
            }
        });

        _edit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void changeProfile() {
        databaseReference.child("user").orderByChild("emailAddress").equalTo(currentUser.getEmailAddress()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                //User childValue;
                String databaseKey = null;

                for (DataSnapshot child : children) {
                    databaseKey = child.getKey();
                }

                if (_confPassword.getText().toString().equals(_password.getText().toString())) {
                    if (!_name.getText().toString().equals(currentUser.getName())) {
                        databaseReference.child("user").child(databaseKey).child("name").setValue(_name.getText().toString());
                        //databaseReference.child("user").child("name").setValue(_name.getText().toString());
                        //currentUser.setName(_name.getText().toString());
                    }
                    if (!_email.getText().toString().equals(currentUser.getEmailAddress())) {
                        Toast.makeText(EditProfile.this,"Email cannot be changed",Toast.LENGTH_LONG).show();
                        //currentUser.setEmailAddress(_email.getText().toString());
                    }
                    if (!_address.getText().toString().equals(currentUser.getHomeAddress())) {
                        databaseReference.child("user").child(databaseKey).child("homeAddress").setValue(_address.getText().toString());
                        //databaseReference.child("user").child(databaseKey).child("")
                        //currentUser.setHomeAddress(_address.getText().toString());
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}