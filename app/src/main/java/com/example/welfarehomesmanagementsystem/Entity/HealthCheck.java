package com.example.welfarehomesmanagementsystem.Entity;

public class HealthCheck {
    private String id;
    public String name;
    public String date;
    public String age;
    public String contact;
    public String hospital;
    public String staff;

    public HealthCheck(String id, String name, String date, String age, String contact, String hospital, String staff) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.age = age;
        this.contact = contact;
        this.hospital = hospital;
        this.staff = staff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}
