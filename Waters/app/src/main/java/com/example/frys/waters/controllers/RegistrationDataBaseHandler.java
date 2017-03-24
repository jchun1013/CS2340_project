package com.example.frys.waters.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.frys.waters.model.User;

/**
 * Created by joon1 on 2017-03-20.
 */

public class RegistrationDataBaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //databse version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static String DATABASE_NAME = "login.db";

    //table name
    private static final String TABLE_REGISTER = "register";

    //table column names
    public static final String Col_USERNAME = "username";
    public static final String Col_NAME = "name";
    public static final String Col_PASSWORD = "password";
    public static final String Col_EMAIL = "email";
    public static final String Col_ADDRESS = "address";
    public static final String Col_USERTYPE = "type";

    //create table sql query
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_REGISTER + " (" + Col_USERNAME
            + " TEXT, " + Col_NAME + " TEXT, " + Col_PASSWORD + " TEXT, " + Col_EMAIL
            + " TEXT, " + Col_ADDRESS + " TEXT, " + Col_USERTYPE + ")";


    public RegistrationDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);

        onCreate(db);
    }

    public void addRegister(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_USERNAME, user.getUsername());
        values.put(Col_NAME, user.getName());
        values.put(Col_PASSWORD, user.getPassword());
        values.put(Col_EMAIL, user.getEmailAddress());
        values.put(Col_ADDRESS, user.getHomeAddress());
        values.put(Col_USERTYPE, user.getUsertype().toString());

        db.insert(TABLE_REGISTER, null, values);
        db.close();
    }

    public void deleteEntry(String username) {
        db.delete(TABLE_REGISTER, Col_USERNAME + " = " + username, null);
    }

    public String getAllNames() {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTER, new String[]{Col_USERNAME}, null, null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            name += cursor.getString(cursor.getColumnIndex(Col_USERNAME)) + " ";
        }
        return name;
    }

    public boolean usernameExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTER, null, "username = ?", new String[]{username}, null,null,null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public String getPassword(String username) {
        String password = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.query(TABLE_REGISTER, null, "username = ?", new String[]{username}, null,null,null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(Col_PASSWORD));
            cursor.close();
        }
        return password;
    }

    public String getName(String username) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.query(TABLE_REGISTER, null, "username = ?", new String[]{username}, null,null,null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(Col_NAME));
            cursor.close();
        }
        return name;
    }

    public String getEmail(String username) {
        String email = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.query(TABLE_REGISTER, null, "username = ?", new String[]{username}, null,null,null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndex(Col_EMAIL));
            cursor.close();
        }
        return email;
    }

    public String getHomeAddress(String username) {
        String address = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.query(TABLE_REGISTER, null, "username = ?", new String[]{username}, null,null,null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            address = cursor.getString(cursor.getColumnIndex(Col_ADDRESS));
            cursor.close();
        }
        return address;
    }

    public String getUserType(String username) {
        String type = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.query(TABLE_REGISTER, null, "username = ?", new String[]{username}, null,null,null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            type = cursor.getString(cursor.getColumnIndex(Col_USERTYPE));
            cursor.close();
        }
        return type;
    }
}
