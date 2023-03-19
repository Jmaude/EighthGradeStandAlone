package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentActivityDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "admin.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    private static final String DATABASE_TABLE = "student";

    public StudentActivityDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
