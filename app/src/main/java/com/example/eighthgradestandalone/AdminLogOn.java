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
    AdminDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        EditText adminId = findViewById(R.id.editAdminID);
        EditText adminPW = findViewById(R.id.editNumberPW);
        DB = new AdminDBHelper(this);

        Button bttnLogOn = findViewById(R.id.buttonLogOn);
        bttnLogOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogOn.this, AdminStudentList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                String aId = adminId.getText().toString();
                String aPass = adminPW.getText().toString();

                boolean checkAd = DB.checkAdmin(aId,aPass);

                if (checkAd == true) {
                    Intent i = new Intent(getApplicationContext(), AdminInputData.class);
                    startActivity(i);
                }
                AdminDataSource ds = new AdminDataSource(AdminLogOn.this);
                boolean wasSuccessful;

                try{
                    ds.open();

                    if(currentAdmin.getAdminID() == -1){
                        wasSuccessful = ds.insertAdmin(currentAdmin);

                        if(wasSuccessful){
                            int newId = ds.getLastAdminID();
                            currentAdmin.setAdminID(newId);
                        }

                    } else {
                        Toast.makeText(AdminLogOn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                    ds.close();
                }
                catch (Exception e){
                    wasSuccessful = false;
                }
            }
        });

    }

}
