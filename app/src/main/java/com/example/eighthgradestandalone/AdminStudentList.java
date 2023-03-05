package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminStudentList extends AppCompatActivity {

    RecyclerView studentList;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int studentSystemID = students.get(position).getStudentSystemID();
            Intent intent = new Intent(AdminStudentList.this, AdminInputData.class);
            intent.putExtra("studentSystemID", studentSystemID);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_list);

        StudentActivityDataSource ds = new StudentActivityDataSource(this);

        try{
            ds.open();
            students = ds.getStudents();
            ds.close();
            if (students.size() > 0) {
                studentList = findViewById(R.id.rvAdminStdList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                studentList.setLayoutManager(layoutManager);
                studentAdapter = new StudentAdapter(students, this);
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
}