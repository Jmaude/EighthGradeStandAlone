package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class StudentLogOn extends AppCompatActivity implements Serializable {

    EditText studentNum;
    EditText studentPass;
    private Student currentStudent;

    // StudentActivityDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_on);

        studentNum = (EditText) findViewById(R.id.textStudentId);
        studentPass = (EditText) findViewById(R.id.textNumberPW2);

       // DB = new StudentActivityDBHelper(this);


        initLogOn();
        initGoMain();



    }

    private void initLogOn() {
        Button logOn = findViewById(R.id.buttonStLogOn);
        logOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String stNum = studentNum.getText().toString();
                String stPass = studentPass.getText().toString();
                StudentActivityDataSource DB = new StudentActivityDataSource(StudentLogOn.this);

                /*int studentSystemID = student.get(position).getStdNum();
            Intent intent = new Intent(AdminStudentList.this, AdminInputData.class);
            intent.putExtra("studentID", studentSystemID);
            startActivity(intent);*/

                try {
                    DB.open();
                    int pw = Integer.parseInt(stPass);
                    int id = Integer.parseInt(stNum);
                    int stdPass = DB.checkStudent(id);
                    if (stdPass == pw) {
                        int studentSystemID = id;
                        Intent intent = new Intent(StudentLogOn.this, StudentInformation.class);
                        //Bundle b = new Bundle();
                        intent.putExtra( "studentID",studentSystemID);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(StudentLogOn.this, "Invalid Student Number", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    private void initGoMain() {
        Button main = findViewById(R.id.buttonStLogOn2);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
            Intent intent = new Intent(StudentLogOn.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);}
        });
}

   /* public Bundle toBundle(Student student) {
        Bundle b = new Bundle();
        b.putString("stdfirstname",student.getStdFirstName());
        b.putString("stdlastname", student. getStdLastName());
        b.putInt("stdNum", student.getStdNum());
        b.putInt("costfp", student.getCostFP());
        b.putInt("costsf", student.getCostSF());
        b.putInt("amountpaid", student.getAmountPaid());
        b.putInt("amountdue", student.getAmountDue());
        b.putString("password", student.getStdpassword());


        return b;
    }*/


       /* bttnLogOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String adID = adminId.getText().toString();
            String adPW = adminPW.getText().toString();
            AdminDataSource adb = new AdminDataSource(AdminLogOn.this);
            try {
                adb.open();
                int pw = Integer.parseInt(adPW);
                int id = Integer.parseInt(adID);
                int adbPassword = adb.checkAdmin(id);
                if (adbPassword == pw) {
                    Intent intent = new Intent(AdminLogOn.this, AdminInputData.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLogOn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

            }

            }
        });*/


        }

