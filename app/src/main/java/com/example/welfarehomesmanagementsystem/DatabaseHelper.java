package com.example.welfarehomesmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "login.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);

    }

    //Create table in database
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE USERS(username TEXT primary key, password TEXT)");
    }

    //Drop the table if exists
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS USERS");
    }

    //Insert data
    public boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password",password);
        long result = MyDB.insert("USERS",null,contentValues);
        if (result == -1){
            return false;
        }
        else
            return true;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM USERS WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

    public boolean checkPassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM USERS WHERE username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

    public  boolean isContainAll(String string){
        String regex="^(?=[A-Za-z])[0-9A-Za-z]{6,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(string);
        boolean isMatch=matcher.matches();
        return isMatch;
    }

    public boolean update(String name, String newpass) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",newpass);
        MyDB.update("USERS",contentValues,"username=?",new String[]{name});
        return true;
    }

}
