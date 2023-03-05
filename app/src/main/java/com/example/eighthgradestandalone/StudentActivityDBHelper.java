package com.example.eighthgradestandalone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentActivityDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentactivity.dp";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_STUDENT_ACTIVITY =
           "create table student (_id integer primary Key AUTOINCREMENT, stdidnum text, stdfirstname text, stdlastname text);";


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
}
