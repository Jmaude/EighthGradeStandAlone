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
            "create table admin (_id integer primary Key AUTOINCREMENT, username text, password text);";


    public AdminDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADMIN);
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