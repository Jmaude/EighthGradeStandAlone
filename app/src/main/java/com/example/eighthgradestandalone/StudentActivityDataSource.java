package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentActivityDataSource {
    private SQLiteDatabase database;
    private StudentActivityDBHelper dbHelper;

    public StudentActivityDataSource(Context context){
        dbHelper = new StudentActivityDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertStudent;

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            String query = "SELECT * FROM student";
            Cursor cursor = database.rawQuery(query,null);

            Student newStudent;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newStudent = new Student();
                newStudent.setStdId(cursor.getString(1));
                newStudent.setStdFirstName(cursor.getString(2));
                newStudent.setStdLastName(cursor.getString(3));
                students.add(newStudent);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            students = new ArrayList<Student>();
        }
        return students;
    }

    public boolean insertStudent(Student s) {
        boolean didSucceed = false;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues initialValues = new ContentValues();

            initialValues.put("stdidnum", s.getStdId());
            initialValues.put("stdfirstname", s.getStdFirstName());
            initialValues.put("stdlastname", s.getStdLastName());

            didSucceed = database.insert("student", null, initialValues) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }


    public boolean updateStudent(Student s) {
        boolean didSucceed = false;
        try {
            Long rowID = (long) s.getStudentSystemID();
            ContentValues updateValues = new ContentValues();
            updateValues.put("stdidnum", s.getStdId());
            updateValues.put("stdfirstname",s.getStdFirstName());
            updateValues.put("stdlastname", s.getStdLastName());

            didSucceed = database.update("student", updateValues, "stdidnum=" + rowID, null) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastStudentSystemID() {
        int lastID;
        try {
            String query = "Select MAX(_id) from student";
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
