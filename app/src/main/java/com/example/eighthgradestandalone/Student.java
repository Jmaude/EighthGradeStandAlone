package com.example.eighthgradestandalone;

public class Student {
    private int stdSystemID;
    private String stdFirstName;
    private String stdLastName;
    private String stdpassword;
    private int stdNum;
    private int costFP;
    private int costSF;
    private int amountPaid;
    private int amountDue;

    public Student() {
        stdSystemID = -1;
        stdpassword = "1234";
        costFP = 50;
        costSF = 150;
        amountPaid = 0;
        amountDue = costFP + costSF;

    }

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
        return costFP;
    }

    public void setCostFP(int costFP) {
        this.costFP = costFP;
    }

    public int getCostSF() {
        return costSF;
    }

    public void setCostSF(int costSF) {
        this.costSF = costSF;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public String getStdpassword() {
        return stdpassword;
    }

    public void setStdpassword(String stdpassword) {
        this.stdpassword = stdpassword;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public String getAmountPaidText(){
        return String.valueOf(amountPaid);
    }



    public String getAmountDueString() {
        return String.valueOf(amountDue);
    }

}
