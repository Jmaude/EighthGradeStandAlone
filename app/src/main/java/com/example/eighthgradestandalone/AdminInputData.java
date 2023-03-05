package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    wasSuccessful = ds.insertStudent(currentStudent);
                    if (wasSuccessful) {
                        wasSuccessful = true;
                    } else {
                        wasSuccessful = ds.updateStudent(currentStudent);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
            }
        });
    }


}