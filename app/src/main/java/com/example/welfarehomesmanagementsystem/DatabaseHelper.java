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

import static java.lang.Integer.parseInt;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "login.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);

    }

    //Create table in database
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE USERS(userId TEXT primary key, username TEXT, password TEXT, position INT DEFAULT 0, birthday TEXT, phone TEXT, gender TEXT, address TEXT)");
    }

    //Drop the table if exists
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(MyDB);
    }

    @Override
    public void onDowngrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        //Drop table if it's already exist
        MyDB.execSQL("DROP TABLE IF EXISTS USERS" );
        //if not exist, we can parse the onCreate method to run
        onCreate(MyDB);
    }

    //Insert data
    public boolean insertData(String userId, String username, String password,int position, String gender){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("username", username);
        contentValues.put("password",password);
        contentValues.put("position",position);
        contentValues.put("gender",gender);
        long result = MyDB.insert("USERS",null,contentValues);
        if (result == -1){
            return false;
        }
        else
            return true;
    }

    public void updateProfile(String uid, String age, String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        if(!age.equals(""))
            MyDB.execSQL("UPDATE USERS SET age = " + Integer.parseInt(age) + " WHERE userId = " +uid);
        if(!phone.equals(""))
            MyDB.execSQL("UPDATE USERS SET phone = " + phone +" WHERE userId = " +uid);
    }

    public void updateName(String uid, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE USERS SET username = " + username + " WHERE userId = " +uid);
    }


    public boolean checkUserId(String userId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM USERS WHERE userId = ?", new String[]{userId});
        if (cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

    public boolean checkPassword(String userId, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM USERS WHERE userId = ? and password = ?", new String[]{userId,password});
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

    public boolean update(String id, String newpass) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",newpass);
        MyDB.update("USERS",contentValues,"userId=?",new String[]{id});
        return true;
    }

    public Cursor getUserById(String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM USERS WHERE userId = ?",  new String[]{uid});
        return result;
    }

}
