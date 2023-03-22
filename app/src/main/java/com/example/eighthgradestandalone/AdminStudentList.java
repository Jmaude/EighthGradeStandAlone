package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
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
        initSettingsButton();
        initDeleteSwitch();


    }

    @Override
    public void onResume() {
        super.onResume();

        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "stdlastname");
        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        StudentActivityDataSource sdb = new StudentActivityDataSource(this);

        try{
            sdb.open();
            student = sdb.getStudents(sortBy, sortOrder);
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

    private void initSettingsButton() {
        ImageButton ibList = findViewById(R.id.buttonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)  {
                Intent intent = new Intent(AdminStudentList.this, StudentActivitySettings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initDeleteSwitch() {
        Switch s = findViewById(R.id.deleteSwitch);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Boolean status = compoundButton.isChecked();
                studentAdapter.setDelete(status);
                studentAdapter.notifyDataSetChanged();
                // redraws the list
            }
        });
    }


}


