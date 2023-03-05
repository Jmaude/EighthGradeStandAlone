package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminInputData extends AppCompatActivity {

    private Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_input_data);
        currentStudent = new Student();
        initTextChangeEvents();
        initSaveButton();


    }
    private void initTextChangeEvents() {

        final EditText etStdNumId = findViewById(R.id.editStdNumId);
        etStdNumId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentStudent.setStdId(etStdNumId.getText().toString());
            }
        });
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



    }

    private void initSaveButton() {

        Button buttonSave = findViewById(R.id.buttonSaveAdminStudent);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful = true;
                StudentActivityDataSource ds = new StudentActivityDataSource(AdminInputData.this);
                try {
                    ds.open();
                    if (currentStudent.getStudentSystemID() == -1) {
                        wasSuccessful = ds.insertStudent(currentStudent);
                        if (wasSuccessful) {
                            int newID = ds.getLastStudentSystemID();
                            currentStudent.setStudentSystemID(newID);
                        } else {
                            wasSuccessful = ds.updateStudent(currentStudent);
                        }
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
            }

        });
    }
}
