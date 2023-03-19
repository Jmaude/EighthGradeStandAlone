package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        TextView fPrice = findViewById(R.id.showFP);
        TextView sfPrice = findViewById(R.id.showSF);
        TextView aPaid = findViewById(R.id.showAP);
        TextView aDue = findViewById(R.id.show_AD);

        fPrice.setText("$75.00");
        sfPrice.setText("$25.00");
        aPaid.setText("$50.00");
        aDue.setText("$50.00");

        initMakePaymentButton();
        initLogoutButton();


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