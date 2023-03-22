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
        Button main = findViewById(R.id.buttonMain);




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
            String adID = adminId.getText().toString();
            String adPW = adminPW.getText().toString();
            AdminDataSource adb = new AdminDataSource(AdminLogOn.this);
            StudentActivityDataSource sdb = new StudentActivityDataSource(AdminLogOn.this);
            try {
                adb.open();
                sdb.open();
                int pw = Integer.parseInt(adPW);
                int id = Integer.parseInt(adID);
                int recordCount;
                int adbPassword = adb.checkAdmin(id);
                if (adbPassword == pw) {
                    recordCount = sdb.countStudentRecord();
                    if (recordCount > 0) {
                        Intent intent = new Intent(AdminLogOn.this, AdminStudentList.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    if (recordCount < 0) {
                        Intent intent = new Intent(AdminLogOn.this, AdminInputData.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(AdminLogOn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

            }
            adb.close();
            sdb.close();

            }
        });

            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    Intent intent = new Intent(AdminLogOn.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);}
            });


}

}
