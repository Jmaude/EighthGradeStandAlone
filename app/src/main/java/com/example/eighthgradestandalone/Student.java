package com.example.eighthgradestandalone;

public class Student {
    private int stdSystemID;
    private String stdFirstName;
    private String stdLastName;
    private int stdNum;
    private int costFP;
    private int costSF;
    private int amountPaid;
    private int amountDue;

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

    public int getStdNum() {
        return stdNum;
    }

    public String getStdNumText() {
        return String.valueOf(stdNum);
    }

    public void setStdNum(int stdNum) {
        this.stdNum = stdNum;
    }

    public int getCostFP() {
        costFP = 50;
        return costFP;
    }

    public void setCostFP(int costFP) {
        this.costFP = costFP;
    }

    public int getCostSF() {
        costSF = 150;
        return costSF;
    }

    public void setCostSF(int costSF) {
        this.costSF = costSF;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int setAmountPaid(int amountPaid) {
       amountPaid = 0;
        return amountPaid;
    }

    public String getAmountPaidText(){
        return String.valueOf(amountPaid);
    }

    public int getAmountDue() {
        amountDue = getCostFP()+getCostSF()-getAmountPaid();
        return amountDue;
    }

    public String getAmountDueString() {
        return String.valueOf(amountDue);
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }
}
