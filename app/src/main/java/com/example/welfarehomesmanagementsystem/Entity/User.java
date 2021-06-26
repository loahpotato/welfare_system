package com.example.welfarehomesmanagementsystem.Entity;

public class User {
    public String id;
    public String name;
    public String password;
    public int position;
    public int age;
    public String phone;
    public String gender;

    public User(String id, String name, String password, int position, int age, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.position = position;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
