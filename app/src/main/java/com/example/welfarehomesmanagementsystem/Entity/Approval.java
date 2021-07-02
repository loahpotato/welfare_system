package com.example.welfarehomesmanagementsystem.Entity;

public class Approval {

    public String item;
    public String date;
    public int amount;
    public String price;
    public String manager;
    public String staff;
    public String others;
    public String status;

    public Approval(String item, String date, int amount, String price, String manager, String staff, String others, String status) {
        this.item = item;
        this.date = date;
        this.amount = amount;
        this.price = price;
        this.manager = manager;
        this.staff = staff;
        this.others = others;
        this.status = status;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
