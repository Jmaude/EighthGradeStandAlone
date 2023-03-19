package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogOn extends AppCompatActivity {

    EditText studentNum;
    EditText studentPass;
   // StudentActivityDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_on);

        studentNum = (EditText) findViewById(R.id.textStudentId);
        studentPass = (EditText) findViewById(R.id.textNumberPW2);

        Button logOn = findViewById(R.id.buttonStLogOn);
        Button main = findViewById(R.id.buttonStLogOn2);
        //DB = new StudentActivityDBHelper(this);

       logOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              /*   String stNum = studentNum.getText().toString();

                boolean checkNum = DB.checkStNum(stNum);
                if (checkNum == true) {
                    Intent intent = new Intent(getApplicationContext(), StudentData.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(StudentLogOn.this, "Invalid Student Number", Toast.LENGTH_SHORT).show();
                }*/
                Intent intent = new Intent(StudentLogOn.this, StudentInformation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

       main.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(StudentLogOn.this, MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
           }
       });
    }
}
