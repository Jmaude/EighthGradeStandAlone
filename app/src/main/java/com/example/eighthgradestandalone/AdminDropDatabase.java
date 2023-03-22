package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminDropDatabase extends AppCompatActivity {

    private Admin currentAdmin;
    AdminDataSource adb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_drop_database);

        EditText adminID = findViewById(R.id.editAdminID);
        EditText adminPW = findViewById(R.id.editNumberPW);
        adb = new AdminDataSource(this);

        Button bttnLogOn = findViewById(R.id.buttonLogOn2);
        Button bttnDelete = findViewById(R.id.deleteButton);
        Button main = findViewById(R.id.buttonMain2);




        bttnLogOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adID = adminID.getText().toString();
                String adPW = adminPW.getText().toString();
                AdminDataSource adb = new AdminDataSource(AdminDropDatabase.this);
                StudentActivityDataSource sdb = new StudentActivityDataSource(AdminDropDatabase.this);
                try {
                    adb.open();
                    sdb.open();
                    int pw = Integer.parseInt(adPW);
                    int id = Integer.parseInt(adID);
                    int recordCount;
                    int adbPassword = adb.checkAdmin(id);
                    if (adbPassword == pw) {
                        recordCount = sdb.countStudentRecord();
                        if (recordCount > 0) {
                            Intent intent = new Intent(AdminDropDatabase.this, AdminStudentList.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        if (recordCount < 0) {
                            Intent intent = new Intent(AdminDropDatabase.this, AdminInputData.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(AdminDropDatabase.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
                adb.close();
                sdb.close();

            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(AdminDropDatabase.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);}
        });

        bttnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adID = adminID.getText().toString();
                String adPW = adminPW.getText().toString();
                boolean wasStudentTableDeleted = true;
                boolean wasAdminTableDeleted = true;
                AdminDataSource adb = new AdminDataSource(AdminDropDatabase.this);
                StudentActivityDataSource sdb = new StudentActivityDataSource(AdminDropDatabase.this);
                try {
                    adb.open();
                    sdb.open();
                    int pw = Integer.parseInt(adPW);
                    int id = Integer.parseInt(adID);
                    int adbPassword = adb.checkAdmin(id);

                    if (adbPassword == pw) {
                        wasStudentTableDeleted = adb.deleteStudentTable();
                        if (wasStudentTableDeleted) {
                            Toast.makeText(AdminDropDatabase.this, "Student Database Cleared", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminDropDatabase.this, "Database was not cleared", Toast.LENGTH_LONG).show();
                        }
                        wasAdminTableDeleted= adb.deleteAdminTable();
                        if (wasAdminTableDeleted) {
                            Toast.makeText(AdminDropDatabase.this, "Student Database Cleared", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminDropDatabase.this, "Database was not cleared", Toast.LENGTH_LONG).show();
                        }
                        }

                } catch(Exception e) {

                }
                Intent intent = new Intent(AdminDropDatabase.this, AdminLogOn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });




    }

}
