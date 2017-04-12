package com.example.frys.waters.controllers;

//import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.frys.waters.model.Location;
//import com.example.frys.waters.model.WaterPurityReport;

class PurityReportDataBaseHandler extends SQLiteOpenHelper {
    //private SQLiteDatabase db;

    //databse version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "PurityReport.db";

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

}