package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdminDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "admin.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_ADMIN =
            "create table admin (_id integer primary key AUTOINCREMENT, admfirstname text, admlastname text, admusername int, admpassword int)";

    private static final String CREATE_TABLE_STUDENT =
            "create table student (_id integer primary Key AUTOINCREMENT, stdfirstname text, stdlastname text, stdNum int," +
                    "costfp Integer default 50.00, costsf integer default 150.00, amountpaid integer default 0.00, amountdue integer default 0.00," +
                    "password integer default 1234);";


    public AdminDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(AdminDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + " to " + newVersion + ", which will destroy all old data");
                onCreate(db);

    }
}