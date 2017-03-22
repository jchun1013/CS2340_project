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
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "SourceReport.db";
    private static final String TABLE_SOURCEREPORT = "sourceReport";

    public static final String KEY_REPORT_NUMBER = "reportNumber";
    public static final String Col_NAME_OF_REPORTER = "nameOfReporter";
    public static final String Col_LOCATION = "location";
    public static final String Col_CONDITION = "condition";
    public static final String Col_WATERTYPE = "waterType";
    public static final String Col_DATETIME = "dateTime";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_SOURCEREPORT + " ("
            + KEY_REPORT_NUMBER + " TEXT, " + Col_NAME_OF_REPORTER + " TEXT, " + Col_LOCATION
            + " TEXT, " + Col_CONDITION + " TEXT, " + Col_WATERTYPE
            + " TEXT, " + Col_DATETIME + ")";

    public SourceReportDataBaseHandler(Context context, Object name, Object factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOURCEREPORT);

        onCreate(db);
    }

    void addSourceReport(WaterSourceReport report) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REPORT_NUMBER, report.getReportNumber());
        values.put(Col_NAME_OF_REPORTER, report.getNameOfReporter());
        values.put(Col_LOCATION, report.getLocation().toString());
        values.put(Col_CONDITION, report.getCondition());
        values.put(Col_WATERTYPE, report.getTypeOfWater());
        values.put(Col_DATETIME, report.getDateTime());

        db.insert(TABLE_SOURCEREPORT, null, values);
        db.close();
    }

    void deleteEntry(String reportNumber) {
        db.delete(TABLE_SOURCEREPORT, KEY_REPORT_NUMBER + " = " + reportNumber, null);
    }

    String getLocations() {
        String coordinates = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SOURCEREPORT, new String[]{Col_LOCATION}, null, null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            coordinates += cursor.getString(cursor.getColumnIndex(Col_LOCATION)) + " ";
        }
        return coordinates;
    }

    String getLocation(String rNumber) {
        String coordinates = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SOURCEREPORT, null, "reportNumber = ?", new String[]{rNumber}, null,null,null);

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            coordinates = cursor.getString(cursor.getColumnIndex(Col_LOCATION));
            cursor.close();
        }
        return coordinates;
    }
//    String getName() {
//        String name = "";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_SOURCEREPORT, new String[] {Col_NAME_OF_REPORTER}, null, null, null, null, null);
//
//    }
}