package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class AdminInputData extends AppCompatActivity {

    private Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_input_data);
        currentStudent = new Student();
        initTextChangeEvents();
        initSaveButton();
        initToggleButton();
        setForEditing(false);
        initListView();


    }
    private void initTextChangeEvents() {

        final EditText etStdFirstName = findViewById(R.id.editStdFirstName);
        etStdFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentStudent.setStdFirstName(etStdFirstName.getText().toString());
            }
        });
        final EditText etStdLastName = findViewById(R.id.editStdLastName);
        etStdLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentStudent.setStdLastName(etStdLastName.getText().toString());

            }
        });
        final EditText etStudentID = findViewById(R.id.editStdNum);
        etStudentID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentStudent.setStdNum(Integer.parseInt(etStudentID.getText().toString()));
            }
        });

    }

    private void initSaveButton() {
        Button btSave = findViewById(R.id.bttnSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful = true;
                StudentActivityDataSource sdb = new StudentActivityDataSource(AdminInputData.this);
                try {
                    sdb.open();
                    if(currentStudent.getStdNum() == -1);
                    wasSuccessful = sdb.insertStudent(currentStudent);
                    if (wasSuccessful) {
                        int newID = sdb.getLastStudentSystemID();
                        currentStudent.setStdNum(newID);
                    } else {
                        wasSuccessful = sdb.updateStudent(currentStudent);
                    }
                    sdb.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                if(wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleEdit);
                    editToggle.toggle();
                    setForEditing(false);
                }



            }
        });
    }


    private void initToggleButton() {
        final ToggleButton editToggle = (ToggleButton) findViewById(R.id.toggleEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editFirstName = findViewById(R.id.editStdFirstName);
        EditText editLastName  = findViewById(R.id.editStdLastName);
        EditText editStdNum = findViewById(R.id.editStdNum);
        editFirstName.setEnabled(enabled);
        editLastName.setEnabled(enabled);
        editStdNum.setEnabled(enabled);
    }

    private void initListView(){
        Button list = findViewById(R.id.bttnList);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminInputData.this, AdminStudentList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


}


