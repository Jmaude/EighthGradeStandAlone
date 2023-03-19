package com.example.eighthgradestandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        initPaymentButton();
        initLogoutButton();

        TextView fpCost = findViewById(R.id.showFoodCost);
        TextView sfCost = findViewById(R.id.showSFcost);
        TextView amPaid = findViewById(R.id.showAmountPaid);
        TextView amDue = findViewById(R.id.showAmountDue);

        fpCost.setText("$75.00");
        sfCost.setText("$25.00");
        amPaid.setText("$50.00");
        amDue.setText("$50.00");




    }

    public void initPaymentButton() {
        Button buttonPay = findViewById(R.id.bttnSave);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentData.this, StudentPayment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void initLogoutButton() {
        Button buttonLogout = findViewById(R.id.bttnList);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentData.this, StudentLogOn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
/*
    private void initToggleButton() {

        final ToggleButton editToggle = (ToggleButton) findViewById(R.id.toggleEdit);

        editToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    private void setForEditing(boolean enabled) {
}*/