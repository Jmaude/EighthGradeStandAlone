package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentInformation extends AppCompatActivity {
    private Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            initStudent(extras.getInt("contactID"));
        }

        initMakePaymentButton();
        initLogoutButton();

    }

    public void initStudent(int id) {
        StudentActivityDataSource ds = new StudentActivityDataSource(StudentInformation.this);
        try {
            ds.open();
            currentStudent = ds.getSpecificStudent(id);
            ds.close();
        }
        catch (Exception e){
            Toast.makeText(this, "Load Student Failed", Toast.LENGTH_LONG).show();
        }

        TextView fPrice = findViewById(R.id.showFP);
        TextView sfPrice = findViewById(R.id.showSF);
        TextView aPaid = findViewById(R.id.showAP);
        TextView aDue = findViewById(R.id.show_AD);

        fPrice.setText(currentStudent.getCostFP());
        sfPrice.setText(currentStudent.getCostSF());
        aPaid.setText(currentStudent.getAmountPaid());
        aDue.setText(currentStudent.getAmountDue());
    }

    public void initMakePaymentButton() {
        Button buttonPay = findViewById(R.id.buttonMakePayment);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentInformation.this, StudentPayment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void initLogoutButton() {
        Button buttonLogout = findViewById(R.id.buttonStdMain);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentInformation.this, StudentLogOn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}