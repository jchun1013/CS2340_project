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
    String password;
    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "login.db";
    private static final String TABLE_REGISTER = "register";
    public static final String Col_USERNAME = "username";
    public static final String Col_NAME = "name";
    public static final String Col_PASSWORD = "password";
    public static final String Col_STANDING = "standing";
    public static final String Col_EMAIL = "email";
    public static final String Col_ADDRESS = "address";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_REGISTER + " ("
            + Col_USERNAME + " TEXT, " + Col_NAME + " TEXT, " + Col_PASSWORD + " TEXT, "
            + Col_EMAIL + " TEXT " + Col_ADDRESS + " TEXT " + Col_STANDING + ")";


    public RegistrationDataBaseHandler(Context context, Object name, Object factory, int version) {
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

    void addRegister(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_USERNAME, user.getUsername());
        values.put(Col_NAME, user.getName());
        values.put(Col_PASSWORD, user.getPassword());
        values.put(Col_STANDING, user.getUsertype().toString());

        db.insert(TABLE_REGISTER, null, values);
        db.close();
    }

    void deleteEntry(String username) {
        db.delete(TABLE_REGISTER, Col_USERNAME + " = " + username, null);
    }

    String getName(String username) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTER, null, "ID = ?", new String[]{username}, null,null,null);
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(Col_NAME));
        }
        return name;
    }

    String getKeyStanding(String username) {
        String standing = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTER, null, "ID = ?", new String[]{username},null,null,null);
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            standing = cursor.getString(cursor.getColumnIndex(Col_STANDING));
        }
        return standing;
    }

    String getRegister(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTER, null, "ID = ?", new String[]{username},null,null,null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        } else if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(Col_PASSWORD));
            cursor.close();
        }
        return password;
    }

    public String getDataBaseName() {
        return DATABASE_NAME;
    }

    public static String getUserName() {
        return Col_USERNAME;
    }

    public static String getTableContacts() {
        return TABLE_REGISTER;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
