package com.example.eighthgradestandalone;

public class Student {

    private int studentSystemID;
    private String stdId;
    private String stdFirstName;
    private String stdLastName;

    public Student() {
        studentSystemID = -1;

    }

    public int getStudentSystemID() {
        return studentSystemID;
    }

    public void setStudentSystemID(int studentSystemID) {
        this.studentSystemID = studentSystemID;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
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
}
