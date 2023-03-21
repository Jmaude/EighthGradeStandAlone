package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
        setForEditing(true);
        initListView();
        initGoMain();

        int stN = getIntent().getIntExtra("studentID",0);
        Bundle b = getIntent().getExtras();
        if (b!=null){
            initStudent(stN);
        }

    }

    public void initStudent(int id) {
        StudentActivityDataSource ds = new StudentActivityDataSource(AdminInputData.this);
        try {
            ds.open();
            currentStudent = ds.getSpecificStudent(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Student Failed", Toast.LENGTH_LONG).show();
        }

        EditText stNum = findViewById(R.id.editStdNum);
        EditText stFName = findViewById(R.id.editStdFirstName);
        EditText stLName = findViewById(R.id.editStdLastName);
        EditText amPaid = findViewById(R.id.editAmountPaidInput);
        TextView amDue = findViewById(R.id.textView9);

        stNum.setText(Integer.toString(currentStudent.getStdNum()));
        stFName.setText(currentStudent.getStdFirstName());
        stLName.setText(currentStudent.getStdLastName());
        amPaid.setText(Integer.toString(currentStudent.getAmountPaid()));
        amDue.setText(Integer.toString(currentStudent.getAmountDue()));
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

        final EditText etAmountPaid = findViewById(R.id.editAmountPaidInput);
        etAmountPaid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentStudent.setAmountPaid(Integer.parseInt(etAmountPaid.getText().toString()));

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
                    if(currentStudent.getStdSystemID() == -1) {
                        wasSuccessful = sdb.insertStudent(currentStudent);
                        if (wasSuccessful) {
                            int newID = sdb.getLastStudentSystemID();
                            currentStudent.setStdNum(newID);
                        }
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
        EditText editPayment = findViewById(R.id.editAmountPaidInput);
        editFirstName.setEnabled(enabled);
        editLastName.setEnabled(enabled);
        editStdNum.setEnabled(enabled);
        editPayment.setEnabled(enabled);

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

    private void initGoMain() {
        Button main = findViewById(R.id.buttonStLogOn2);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(AdminInputData.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);}
        });
    }


}


