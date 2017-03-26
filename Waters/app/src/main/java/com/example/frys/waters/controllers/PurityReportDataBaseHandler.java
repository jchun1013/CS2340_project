package com.example.frys.waters.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.frys.waters.model.WaterPurityReport;

/**
 * Created by joon1 on 2017-03-24.
 */

public class PurityReportDataBaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //databse version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static String DATABASE_NAME = "PurityReport.db";

    //table name
    private static final String TABLE_PURITYREPORT = "purityReport";

    //table column names
    public static final String Col_REPORT_NUMBER = "reportNumber";
    public static final String Col_NAME_OF_WORKER = "nameOfWorker";
    public static final String Col_LOCATION = "location";
    public static final String Col_CONDITION = "condition";
    public static final String Col_VIRUS_PPM = "virusPPM";
    public static final String Col_CONTAMINANT_PPM = "contaminantPPM";
    public static final String Col_DATETIME = "dateTime";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_PURITYREPORT + " ("
            + Col_REPORT_NUMBER + " TEXT, " + Col_NAME_OF_WORKER + " TEXT, " + Col_LOCATION
            + " TEXT, " + Col_CONDITION + " TEXT, " + Col_VIRUS_PPM + " TEXT, " + Col_CONTAMINANT_PPM
            + " TEXT, " + Col_DATETIME + ")";

    public PurityReportDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURITYREPORT);

        onCreate(db);
    }

    public void addPurityReport(WaterPurityReport report) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_REPORT_NUMBER, report.getReportNumber());
        values.put(Col_NAME_OF_WORKER, report.getNameOfWorker());
        values.put(Col_LOCATION, report.getLocation().toString());
        values.put(Col_CONDITION, report.getCondition());
        values.put(Col_VIRUS_PPM, Double.toString(report.getVirusPPM()));
        values.put(Col_CONTAMINANT_PPM, Double.toString(report.getContaminantPPM()));
        values.put(Col_DATETIME, report.getDateTime());

        //inserting row
        db.insert(TABLE_PURITYREPORT, null, values);
        db.close();
    }

    public String getLocation(int rNumber) {
        String coordinates = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from purityReport where reportNumber=" + rNumber + "", null );

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            coordinates = cursor.getString(cursor.getColumnIndex(Col_LOCATION));
            cursor.close();
        }
        return coordinates;
    }

    public String getNameOfWorker(int rNumber) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from purityReport where reportNumber=" + rNumber + "", null );

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(Col_NAME_OF_WORKER));
            cursor.close();
        }
        return name;
    }

    public String getCondition(int rNumber) {
        String condition = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from purityReport where reportNumber=" + rNumber + "", null );

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            condition = cursor.getString(cursor.getColumnIndex(Col_CONDITION));
            cursor.close();
        }
        return condition;
    }

    public double getVirusPPM(int rNumber) {
        String ppm = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from purityReport where reportNumber=" + rNumber + "", null );

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            ppm = cursor.getString(cursor.getColumnIndex(Col_VIRUS_PPM));
            cursor.close();
        }
        return Double.parseDouble(ppm);
    }

    public double getConditionPPM(int rNumber) {
        String ppm = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from purityReport where reportNumber=" + rNumber + "", null );

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            ppm = cursor.getString(cursor.getColumnIndex(Col_CONTAMINANT_PPM));
            cursor.close();
        }
        return Double.parseDouble(ppm);
    }

    public String getDateTime(int rNumber) {
        String dateTime = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from purityReport where reportNumber=" + rNumber + "", null );
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            dateTime = cursor.getString(cursor.getColumnIndex(Col_DATETIME));
        }
        cursor.close();
        return dateTime;
    }

    public int countReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
