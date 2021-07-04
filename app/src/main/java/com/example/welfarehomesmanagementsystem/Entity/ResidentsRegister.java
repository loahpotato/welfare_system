package com.example.welfarehomesmanagementsystem.Entity;

public class ResidentsRegister {
    private String id;
    public String name;
    public String date;
    public String gender;
    public String age;
    public String relative;
    public String contact;
    public String note;
    public String current_user;

    public ResidentsRegister(String id, String name, String date, String gender, String age, String relative, String contact, String note, String current_user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.age = age;
        this.relative = relative;
        this.contact = contact;
        this.note = note;
        this.current_user = current_user;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(String current_user) {
        this.current_user = current_user;
    }
}
