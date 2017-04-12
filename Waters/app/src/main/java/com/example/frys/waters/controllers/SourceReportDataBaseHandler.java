package com.example.frys.waters.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.frys.waters.model.Location;
import com.example.frys.waters.model.WaterSourceReport;


/**
 * Created by joon1 on 2017-03-20.
 */

public class SourceReportDataBaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //database version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static String DATABASE_NAME = "SourceReport.db";

    //table name
    private static final String TABLE_SOURCEREPORT = "sourceReport";

    //table column names
    private static final String Col_REPORT_NUMBER = "reportNumber";
    private static final String Col_NAME_OF_REPORTER = "nameOfReporter";
    private static final String Col_LOCATION = "location";
    private static final String Col_CONDITION = "condition";
    private static final String Col_WATERTYPE = "waterType";
    private static final String Col_DATETIME = "dateTime";

    //create table sql query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_SOURCEREPORT + " ("
            + Col_REPORT_NUMBER + " TEXT, " + Col_NAME_OF_REPORTER + " TEXT, " + Col_LOCATION
            + " TEXT, " + Col_CONDITION + " TEXT, " + Col_WATERTYPE
            + " TEXT, " + Col_DATETIME + ")";

    /**
     * constructor
     * @param context is name of the class this method is being called from + .this
     */
    public SourceReportDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //execute CREATE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOURCEREPORT);

        onCreate(db);
    }

    /**
     * Adds new report to the database
     * @param report report
     */
    public void addSourceReport(WaterSourceReport report) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_REPORT_NUMBER, report.getReportNumber());
        values.put(Col_NAME_OF_REPORTER, report.getNameOfReporter());
        values.put(Col_LOCATION, report.getLocation().toString());
        values.put(Col_CONDITION, report.getCondition());
        values.put(Col_WATERTYPE, report.getTypeOfWater());
        values.put(Col_DATETIME, report.getDateTime());

        //inserting row
        db.insert(TABLE_SOURCEREPORT, null, values);
        db.close();
    }

    /**
     * This method gets the number of total reports(rows) that are in the database
     * @return number of reports
     */
    private int countReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sourceReport", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}

