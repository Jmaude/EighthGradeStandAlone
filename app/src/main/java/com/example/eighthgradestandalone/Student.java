package com.example.eighthgradestandalone;

import android.widget.CheckBox;
import android.widget.RadioButton;

public class Student {
    private int stdSystemID;
    private String stdFirstName;
    private String stdLastName;
    private String stdNum;
    private String costFP;
    private String costSF;
    private String amountPaid;
    private String amountDue;

    public int getStdSystemID() {
        return stdSystemID;
    }

    public void setStdSystemID(int stdSystemID) {
        this.stdSystemID = stdSystemID;
    }

    public String getStdFirstName() {
        return stdFirstName;
    }

    public void setStdFirstName(String stdFirstName) {
        this.stdFirstName = stdFirstName;
    }

    public String getStdLastName() {
        return stdLastName;
    }

    public void setStdLastName(String stdLastName) {
        this.stdLastName = stdLastName;
    }

    public String getStdNum() {
        return stdNum;
    }

    public void setStdNum(String stdNum) {
        this.stdNum = stdNum;
    }

    public String getCostFP() {
        costFP = "50.00";
        return costFP;
    }

    public void setCostFP(String costFP) {
        this.costFP = costFP;
    }

    public String getCostSF() {
        costSF = "150.00"
        return costSF;
    }

    public void setCostSF(String costSF) {
        this.costSF = costSF;
    }

    public String getAmountPaid() {
        amountDue = "200.00";
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }
}
