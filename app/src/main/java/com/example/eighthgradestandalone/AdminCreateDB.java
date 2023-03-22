package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminCreateDB extends AppCompatActivity {

    private Admin currentAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_db);
        currentAdmin = new Admin();
        initTextChangeEvents();
        iniSaveButton();
        hideKeyboard();


    }
    private void initTextChangeEvents() {

        final EditText etAdminFirstName = findViewById(R.id.editStdFirstName);
        etAdminFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentAdmin.setAdminFirstName(etAdminFirstName.getText().toString());
            }
        });
        final EditText etAdminLastName = findViewById(R.id.editStdLastName);
        etAdminLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentAdmin.setAdminLastName(etAdminLastName.getText().toString());
            }
        });
        final EditText etAdminUserName = findViewById(R.id.editAdminUserName);
        etAdminUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentAdmin.setAdUserID(etAdminUserName.getText().toString());

            }
        });
        final EditText etPassword = findViewById(R.id.editPassword);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentAdmin.setAdminPassword(Integer.parseInt(etPassword.getText().toString()));
            }
        });
    }

    private void iniSaveButton() {
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful = true;
                AdminDataSource adb = new AdminDataSource(AdminCreateDB.this);
                try {
                    int recordCount;
                    adb.open();
                    recordCount = adb.checkDatabaseCreated();
                    if (recordCount > 0) {
                        Toast.makeText(AdminCreateDB.this, "Database Already Registered, to delete update drop the table after admin logon ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminCreateDB.this, AdminLogOn.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        currentAdmin.getAdminID();
                        adb.insertAdmin(currentAdmin);
                        Intent intent = new Intent(AdminCreateDB.this, AdminInputData.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    adb.close();

                } catch (Exception e) {
                    Toast.makeText(AdminCreateDB.this, "Database Was Not Created", Toast.LENGTH_SHORT).show();;
                }

            }
        });
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editStdFirstName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editStdLastName);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText editCity = findViewById(R.id.editAdminUserName);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);
    }



    }