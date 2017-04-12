package com.example.frys.waters.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.frys.waters.model.Location;
import com.example.frys.waters.model.WaterPurityReport;

class PurityReportDataBaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //databse version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static String DATABASE_NAME = "PurityReport.db";

    //table name
    private static final String TABLE_PURITYREPORT = "purityReport";

    //table column names
    private static final String Col_REPORT_NUMBER = "reportNumber";
    private static final String Col_NAME_OF_WORKER = "nameOfWorker";
    private static final String Col_LOCATION = "location";
    private static final String Col_CONDITION = "condition";
    private static final String Col_VIRUS_PPM = "virusPPM";
    private static final String Col_CONTAMINANT_PPM = "contaminantPPM";
    private static final String Col_DATETIME = "dateTime";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_PURITYREPORT + " ("
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

    public Location getLocation(int rNumber) {
        String coordinates = "";
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_SOURCEREPORT, new String[]{Col_LOCATION}, Col_REPORT_NUMBER + "= ?", new String[]{rNumber}, null,null,null);
        Cursor cursor = db.rawQuery("select * from purityReport where reportNumber=" + rNumber + "", null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            coordinates = cursor.getString(cursor.getColumnIndex(Col_LOCATION));
            cursor.close();
        }
        String[] eachLoc = coordinates.split(",");
        return new Location(Double.parseDouble(eachLoc[0]), Double.parseDouble(eachLoc[1]));
    }

    private String getNameOfWorker(int rNumber) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport where reportNumber=" + rNumber + "", null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(Col_NAME_OF_WORKER));
            cursor.close();
        }
        return name;
    }

    private String getCondition(int rNumber) {
        String condition = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport where reportNumber=" + rNumber + "", null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            condition = cursor.getString(cursor.getColumnIndex(Col_CONDITION));
            cursor.close();
        }
        return condition;
    }

    private double getVirusPPM(int rNumber) {
        String ppm = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport where reportNumber=" + rNumber + "", null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            ppm = cursor.getString(cursor.getColumnIndex(Col_VIRUS_PPM));
            cursor.close();
        }
        return Double.parseDouble(ppm);
    }

    private double getConditionPPM(int rNumber) {
        String ppm = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport where reportNumber=" + rNumber + "", null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            ppm = cursor.getString(cursor.getColumnIndex(Col_CONTAMINANT_PPM));
            cursor.close();
        }
        return Double.parseDouble(ppm);
    }

    private String getDateTime(int rNumber) {
        String dateTime = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport where reportNumber=" + rNumber + "", null);
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            dateTime = cursor.getString(cursor.getColumnIndex(Col_DATETIME));
        }
        cursor.close();
        return dateTime;
    }

    private int countReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from purityReport", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}