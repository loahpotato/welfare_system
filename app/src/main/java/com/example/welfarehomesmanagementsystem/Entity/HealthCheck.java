package com.example.welfarehomesmanagementsystem.Entity;

public class HealthCheck {
    public String name;
    public String date;
    public String age;
    public String contact;
    public String hospital;
    public String staff;

    public HealthCheck(String name, String date, String age, String contact, String hospital, String staff) {
        this.name = name;
        this.date = date;
        this.age = age;
        this.contact = contact;
        this.hospital = hospital;
        this.staff = staff;
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
