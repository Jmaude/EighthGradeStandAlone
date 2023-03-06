package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


    }
    private void initTextChangeEvents() {

        final EditText etStdFirstName = findViewById(R.id.editStdNameFirst);
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
        final EditText etStdLastName = findViewById(R.id.editStdNameLast);
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
                currentStudent.setStdNum(etStudentID.getText().toString());
            }
        });



    }

    private void initSaveButton() {

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful = true;
                StudentActivityDataSource ds = new StudentActivityDataSource(AdminInputData.this);
                try {
                    ds.open();
                    if (currentStudent.getStdSystemID() == -1) {
                        wasSuccessful = ds.insertStudent(currentStudent);
                        if (wasSuccessful) {
                            int newID = ds.getLastStudentSystemID();
                            currentStudent.setStdSystemID(newID);
                        } else {
                            wasSuccessful = ds.updateStudent(currentStudent);
                        }
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                Intent intent = new Intent(AdminInputData.this, AdminStudentList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
        EditText editFirstName = findViewById(R.id.editStdNameFirst);
        EditText editLastName  = findViewById(R.id.editStdNameLast);
        EditText editStdNum = findViewById(R.id.editStdNum);
        editFirstName.setEnabled(enabled);
        editLastName.setEnabled(enabled);
        editStdNum.setEnabled(enabled);
    }


}


