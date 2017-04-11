package com.example.frys.waters.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joon1 on 2017-04-10.
 */
public class RegistrationTest {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Before
    public void setup() throws Exception{
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Test
    public void registerUserTest() throws Exception {
        Registration r = new Registration();

    }

}