package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "admin.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    private static final String DATABASE_TABLE = "admin";


    private static final String CREATE_TABLE_ADMIN =
            "create table admin (_id integer primary key AUTOINCREMENT, admfirstname text, admlastname text, admusername int, admpassword int)";

    private static final String CREATE_TABLE_STUDENT =
            "create table student (_id integer primary Key AUTOINCREMENT, stdfirstname text, stdlastname text, stdNum text," +
                    "costfp Integer default 50.00, costsf integer default 150.00, amountpaid integer default 0.00, amountdue integer default 0.00);";


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

    }
    public boolean checkAdmin(String username, String password) {
        database = this.getWritableDatabase();
        Cursor mCursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE username=? AND password=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
}