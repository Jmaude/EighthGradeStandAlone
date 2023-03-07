package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentActivityDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentactivity.dp";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    private static final String DATABASE_TABLE = "student";



    private static final String CREATE_TABLE_STUDENT_ACTIVITY =
           "create table student (_id integer primary Key AUTOINCREMENT, stdfirstname text, stdlastname text, stdNum text,"
            + "costfp integer, costsf integer, amountpaid integer, amountdue integer);";


    public StudentActivityDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT_ACTIVITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("ALTER TABLE contact ADD COLUMN contactphoto blob");
        }
        catch (Exception e) {
            //do nothing
        }
    }



    public boolean checkStNum(String studentNum) {
        database = this.getWritableDatabase();
        Cursor mCursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE stdNum=?", new String[]{studentNum});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
}
