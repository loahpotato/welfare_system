package com.example.welfarehomesmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper_ResidentsRegister extends SQLiteOpenHelper {
    //Declare variable name for database, table and column
    public static final String DATABASE_NAME = "ResidentsRegister.db";
    public static final String TABLE_NAME = "ResidentsRegister_table";
    public static final String COLUMN_1 = "NAME";
    public static final String COLUMN_2 = "DATE";
    public static final String COLUMN_3 = "GENDER";
    public static final String COLUMN_4 = "AGE";
    public static final String COLUMN_5 = "RELATIVE";
    public static final String COLUMN_6 = "CONTACT";
    public static final String COLUMN_7 = "NOTE";
    public static final String COLUMN_8 = "CURRENT_USER";
    public DbHelper_ResidentsRegister(@Nullable Context context) {
        super(context,DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(NAME TEXT, DATE TEXT, GENDER TEXT,AGE TEXT,RELATIVE TEXT, CONTACT TEXT, NOTE TEXT, CURRENT_USER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table if it's already exist
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME );
        //if not exist, we can parse the onCreate method to run
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table if it's already exist
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME );
        //if not exist, we can parse the onCreate method to run
        onCreate(db);
    }
    public boolean insertData(String name,String date, String gender,String age, String relative, String contact, String note, String current_user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,name);
        contentValues.put(COLUMN_2,date);
        contentValues.put(COLUMN_3,gender);
        contentValues.put(COLUMN_4,age);
        contentValues.put(COLUMN_5,relative);
        contentValues.put(COLUMN_6,contact);
        contentValues.put(COLUMN_7,note);
        contentValues.put(COLUMN_8,current_user);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;
    }
    public Cursor getDataByUser(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT *  FROM "  + TABLE_NAME + " WHERE STAFF = ?",  new String[]{userId});
        return result;
    }

    public boolean checkRepeat(String name, String age, String contact){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT *  FROM "  + TABLE_NAME + " WHERE NAME = ?",  new String[]{name});
        while(result.moveToNext()){
            if(result.getString(4).equals(age) && result.getString(6).equals(contact))
                return false;
        }
        return true;
    }

    public Integer deleteData(String name){
        SQLiteDatabase db =getWritableDatabase();
        return db.delete(TABLE_NAME,"NAME = ?", new String[]{name});
    }
}
