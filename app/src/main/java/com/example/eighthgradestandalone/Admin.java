package com.example.eighthgradestandalone;

public class Admin {
    private int adminID;
    private String adUserID;
    private String adminFirstName;
    private String adminLastName;
    private int adminPassword;


    public Admin() {
        adminID = -1;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdUserID() {
        return adUserID;
    }

    public void setAdUserID(String adUserID) {
        this.adUserID = adUserID;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public int getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(int adminPassword) {
        this.adminPassword = adminPassword;
    }


}
