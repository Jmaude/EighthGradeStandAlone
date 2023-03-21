package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminDataSource {
    private SQLiteDatabase database;
    private AdminDBHelper dbHelper;

    public AdminDataSource(Context context){
        dbHelper = new AdminDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Admin> getAdmin() {
        ArrayList<Admin> admin = new ArrayList<Admin>();
        try {
            String query = "SELECT * FROM admin";
            Cursor cursor = database.rawQuery(query,null);

            Admin newAdmin;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newAdmin = new Admin();
                newAdmin.setAdUserID(cursor.getString(1));
                newAdmin.setAdminPassword(Integer.parseInt(cursor.getString(2)));
                admin.add(newAdmin);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            admin = new ArrayList<Admin>();
        }
        return admin;
    }

    public boolean insertAdmin(Admin a) {
        boolean didSucceed = false;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues initialValues = new ContentValues();

            initialValues.put("admfirstname", a.getAdminFirstName());
            initialValues.put("admlastname", a.getAdminLastName());
            initialValues.put("admusername", a.getAdUserID());
            initialValues.put("admpassword", a.getAdminPassword());

            didSucceed = database.insert("admin", null, initialValues) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }


    public int getLastAdminID() {
        int lastID;
        try {
            String query = "Select MAX(_id) from admin";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastID = -1;
        }
        return lastID;
    }
    public int checkAdmin(int username) {
        int password;

        try {

            String query = "SELECT * FROM admin WHERE admusername = " + username;
            Cursor mCursor = database.rawQuery(query,null);
            mCursor.moveToFirst();
            password = mCursor.getInt(4);

        } catch (Exception e) {
            password = -1;
        }
        return password;
    }

    public int checkDatabaseCreated(){
        int recordCount;
        try {
            String query = "Select COUNT(_id) from admin";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            recordCount = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            recordCount = -1;
        }
        return recordCount;
    }



}
