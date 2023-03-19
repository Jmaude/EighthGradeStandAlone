package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogOn extends AppCompatActivity {
    private Admin currentAdmin;
    AdminDataSource adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        EditText adminId = findViewById(R.id.editAdminID);
        EditText adminPW = findViewById(R.id.editNumberPW);
        adb = new AdminDataSource(this);

        Button bttnLogOn = findViewById(R.id.buttonLogOn);
        Button bttnRegister = findViewById(R.id.registerButton);


        bttnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogOn.this, AdminCreateDB.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        bttnLogOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogOn.this, AdminStudentList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                String aId = adminId.getText().toString();
                String aPass = adminPW.getText().toString();

                boolean checkAd = adb.checkAdmin(aId, aPass);

                if (checkAd == true) {
                    Intent i = new Intent(getApplicationContext(), AdminInputData.class);
                    startActivity(i);
                } else {
                    Toast.makeText(AdminLogOn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

    });

}

}
