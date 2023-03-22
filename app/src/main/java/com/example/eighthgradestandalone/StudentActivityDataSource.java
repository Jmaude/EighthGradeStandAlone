package com.example.eighthgradestandalone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class StudentActivityDataSource {
    private SQLiteDatabase database;
    private AdminDBHelper dbHelper;

    public StudentActivityDataSource(Context context){
        dbHelper = new AdminDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Student> getStudents(String sortField, String sortOrder) {
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            String query = "SELECT * FROM student ORDER BY " + sortField + " " + sortOrder;
            Cursor cursor = database.rawQuery(query,null);

            Student newStudent;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newStudent = new Student();
                newStudent.setStdFirstName(cursor.getString(1));
                newStudent.setStdLastName(cursor.getString(2));
                newStudent.setStdNum(Integer.parseInt(cursor.getString(3)));
                newStudent.setCostFP(Integer.parseInt(cursor.getString(4)));
                newStudent.setCostSF(Integer.parseInt(cursor.getString(5)));
                newStudent.setAmountPaid(Integer.parseInt(cursor.getString(6)));
                newStudent.setAmountDue(Integer.parseInt(cursor.getString(7)));
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

            initialValues.put("stdfirstname", s.getStdFirstName());
            initialValues.put("stdlastname", s.getStdLastName());
            initialValues.put("stdNum", s.getStdNum());
            initialValues.put("costfp", s.getCostFP());
            initialValues.put("costsf", s.getCostSF());
            initialValues.put("amountpaid", s.getAmountPaid());
            initialValues.put("amountdue", s.getAmountDue());
            initialValues.put("password", s.getStdpassword());

            didSucceed = database.insert("student", null, initialValues) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }


    public boolean updateStudent(Student s) {
        boolean didSucceed = false;
        try {
            Long rowID = (long) s.getStdSystemID();
            ContentValues updateValues = new ContentValues();
            updateValues.put("stdfirstname", s.getStdFirstName());
            updateValues.put("stdlastname", s.getStdLastName());
            updateValues.put("stdNum", s.getStdNum());
            updateValues.put("costfp", s.getCostFP());
            updateValues.put("costsf", s.getCostSF());
            updateValues.put("amountpaid", s.getAmountPaid());
            updateValues.put("amountdue", (s.getAmountDue()-s.getAmountPaid()));
            updateValues.put("password", s.getStdpassword());

            didSucceed = database.update("student", updateValues, "_id=" + rowID, null) > 0;
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

    public Student getSpecificStudent(int studentId){
        Student student = new Student();
        String query = "SELECT * FROM student WHERE stdNum=" + studentId;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            student.setStdSystemID(cursor.getInt(0));
            student.setStdFirstName(cursor.getString(1));
            student.setStdLastName(cursor.getString(2));
            student.setStdNum(cursor.getInt(3));
            student.setCostFP(cursor.getInt(4));
            student.setCostSF(cursor.getInt(5));
            student.setAmountPaid(cursor.getInt(6));
            student.setAmountDue(cursor.getInt(7));
            student.setStdpassword(cursor.getString(8));

            cursor.close();
        }
        return student;
    }

    public int checkStudent(int username) {
        int password;

        try {

            String query = "SELECT * FROM student WHERE stdNum = " + username;
            Cursor mCursor = database.rawQuery(query,null);
            mCursor.moveToFirst();
            password = mCursor.getInt(8);

        } catch (Exception e) {
            password = -1;
        }
        return password;
    }

    public int countStudentRecord() {
        int recordCount;
        try {
            String query = "Select COUNT(_id) from student";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            recordCount = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            recordCount = -1;
        }
        return recordCount;
    }

    public boolean deleteContact (int studentId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("student", "stdNum", null) > 0;
        } catch (Exception e) {
            // Do nothing -return value already set to false
        }

        return didDelete;
    }


}
