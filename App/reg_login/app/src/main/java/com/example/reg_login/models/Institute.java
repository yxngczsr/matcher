package com.example.reg_login.models;

public class Institute {

    public Institute(String universityName, String location, String hours, String contact) {
        this.universityName = universityName;
        this.location = location;
        this.hours = hours;
        this.contact = contact;
    }

    public Institute(){}
    public  String universityName;
    public String location;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String hours;
    public String contact;

}
