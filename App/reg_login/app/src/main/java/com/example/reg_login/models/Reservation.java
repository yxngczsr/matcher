package com.example.reg_login.models;

public class Reservation {
    private String id;
    private String fieldId;
    private String userId;
    private String date;
    private String time;

    public Reservation() {
    }

    public Reservation(String id, String fieldId, String userId, String date, String time) {
        this.id = id;
        this.fieldId = fieldId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
