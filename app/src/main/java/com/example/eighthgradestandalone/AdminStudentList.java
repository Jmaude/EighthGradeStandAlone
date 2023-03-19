package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminStudentList extends AppCompatActivity {

    RecyclerView studentList;
    ArrayList<Student> student;
    StudentAdapter studentAdapter;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int studentSystemID = student.get(position).getStdNum();
            Intent intent = new Intent(AdminStudentList.this, AdminInputData.class);
            intent.putExtra("studentID", studentSystemID);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_list);
        initAddStudent();


    }

    @Override
    public void onResume() {
        super.onResume();

        StudentActivityDataSource sdb = new StudentActivityDataSource(this);

        try{
            sdb.open();
            student = sdb.getStudents();
            sdb.close();
            if (student.size() > 0) {
                studentList = findViewById(R.id.rvAdminStdList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                studentList.setLayoutManager(layoutManager);
                studentAdapter = new StudentAdapter(student, this);
                studentAdapter.setOnItemClickListener(onItemClickListener);
                studentList.setAdapter(studentAdapter);
            } else {
                Intent intent = new Intent(AdminStudentList.this, AdminInputData.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } catch(Exception e) {
            Toast.makeText(this, "Error Retrieving Student List", Toast.LENGTH_LONG).show();
        }
    }

    public void initAddStudent() {
        ImageButton newStudent = findViewById(R.id.buttonAdd);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminStudentList.this, AdminInputData.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }






}


