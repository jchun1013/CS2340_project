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

    public void addSourceReport(WaterSourceReport report) {
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

    public void deleteEntry(int reportNumber) {
        db.delete(TABLE_SOURCEREPORT, KEY_REPORT_NUMBER + " = " + reportNumber, null);
    }

    public String getAllLocations() {
        String coordinates = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SOURCEREPORT, new String[]{Col_LOCATION}, null, null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            coordinates += cursor.getString(cursor.getColumnIndex(Col_LOCATION)) + " ";
        }
        return coordinates;
    }

    public String getLocation(int rNumber) {
        String coordinates = "";
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_SOURCEREPORT, new String[]{Col_LOCATION}, KEY_REPORT_NUMBER + "= ?", new String[]{rNumber}, null,null,null);
        Cursor cursor =  db.rawQuery( "select * from sourceReport where reportNumber=" + rNumber + "", null );

        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            coordinates = cursor.getString(cursor.getColumnIndex(Col_LOCATION));
            cursor.close();
        }
        return coordinates;
    }

    public String getReporterName(int rNumber) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from sourceReport where reportNumber=" + rNumber + "", null );
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(Col_NAME_OF_REPORTER));
        }
        return name;
    }

    public String getCondition(int rNumber) {
        String condition = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from sourceReport where reportNumber=" + rNumber + "", null );
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            condition = cursor.getString(cursor.getColumnIndex(Col_CONDITION));
        }
        return condition;
    }

    public String getWaterType(int rNumber) {
        String waterType = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from sourceReport where reportNumber=" + rNumber + "", null );
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            waterType = cursor.getString(cursor.getColumnIndex(Col_WATERTYPE));
        }
        return waterType;
    }

    public String getDateTime(int rNumber) {
        String dateTime = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from sourceReport where reportNumber=" + rNumber + "", null );
        if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            dateTime = cursor.getString(cursor.getColumnIndex(Col_DATETIME));
        }
        return dateTime;
    }

    public int countReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sourceReport", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int[] getAllReportNum() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sourceReport", null);
        String nums = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            nums += cursor.getString(cursor.getColumnIndex(KEY_REPORT_NUMBER)) + " ";
        }
        String[] stringNums = nums.split(" ");
        int[] reportNums = new int[stringNums.length];
        for(int i = 0; i < reportNums.length; i++) {
            reportNums[i] = Integer.parseInt(stringNums[i]);
        }
        return reportNums;
    }
}

