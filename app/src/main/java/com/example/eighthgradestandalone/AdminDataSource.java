package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
                newAdmin.setAdminId(cursor.getString(1));
                newAdmin.setAdminPassword(cursor.getString(2));
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

            initialValues.put("username", a.getAdminID());
            initialValues.put("password", a.getAdminPassword());

            didSucceed = database.insert("admin", null, initialValues) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }


   /* public boolean updateAdmin(Admin a) {
       boolean didSucceed = false;
        try {
            Long rowID = (long) s.getStdSystemID();
            ContentValues updateValues = new ContentValues();
            updateValues.put("stdfirstname", s.getStdFirstName());
            updateValues.put("stdlastname", s.getStdLastName());
            updateValues.put("stdidnum", s.getStdNum());

            didSucceed = database.update("admin", updateValues, "stdidnum=" + rowID, null) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
        }
        */


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
}
