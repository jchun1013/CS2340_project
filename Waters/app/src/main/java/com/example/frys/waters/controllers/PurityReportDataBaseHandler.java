package com.example.frys.waters.controllers;

import android.content.ContentValues;
import android.content.Context;
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

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_PURITYREPORT + " ("
            + Col_REPORT_NUMBER + " TEXT, " + Col_NAME_OF_WORKER + " TEXT, " + Col_LOCATION
            + " TEXT, " + Col_CONDITION + " TEXT, " + Col_VIRUS_PPM
            + " TEXT, " + Col_CONTAMINANT_PPM + ")";

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

    public void addSourceReport(WaterPurityReport report) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_REPORT_NUMBER, report.getReportNumber());
        values.put(Col_NAME_OF_WORKER, report.getNameOfWorker());
        values.put(Col_LOCATION, report.getLocation().toString());
        values.put(Col_CONDITION, report.getCondition());
        values.put(Col_VIRUS_PPM, report.getVirusPPM());
        values.put(Col_CONTAMINANT_PPM, report.getContaminantPPM());

        //inserting row
        db.insert(TABLE_PURITYREPORT, null, values);
        db.close();
    }
}
