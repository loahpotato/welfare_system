package com.example.welfarehomesmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper_Procurement extends SQLiteOpenHelper {
    //Declare variable name for database, table and column
    public static final String DATABASE_NAME = "Procure.db";
    public static final String TABLE_NAME = "PROCUREMENT";
    public static final String COLUMN_1 = "Item";
    public static final String COLUMN_2 = "Amount";
    public static final String COLUMN_3 = "Price";
    public static final String COLUMN_4 = "Staff";
    public static final String COLUMN_5 = "Manager";
    public static final String COLUMN_6 = "Date";
    public static final String COLUMN_7 = "Others";
    public static final String COLUMN_8 = "Status";
    public DbHelper_Procurement(@Nullable Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(Item TEXT, Amount INT, Price TEXT,Staff TEXT,Manager TEXT, Date TEXT, Others TEXT, Status TEXT)");
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
    public boolean insertData(String item,int amount, String price,String staff, String manager, String date, String others){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,item);
        contentValues.put(COLUMN_2,amount);
        contentValues.put(COLUMN_3,price);
        contentValues.put(COLUMN_4,staff);
        contentValues.put(COLUMN_5,manager);
        contentValues.put(COLUMN_6,date);
        contentValues.put(COLUMN_7,others);
        contentValues.put(COLUMN_8,"Waiting");
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;
    }
    public Cursor getDataByUser(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT *  FROM "  + TABLE_NAME + " WHERE Staff = ?",  new String[]{userId});
        return result;
    }

    public Cursor getDataByManager(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT rowid, *  FROM "  + TABLE_NAME + " WHERE Manager = ?",  new String[]{userId});
        return result;
    }

    public boolean updateStatus(int rid, String status) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status",status);
        MyDB.update(TABLE_NAME,contentValues,"rowid = " + rid,null);
        return true;
    }

    public Cursor getByRowId(int rid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rowid = " + rid,null);
        return result;
    }

    public Integer deleteData(String rid){
        SQLiteDatabase db =getWritableDatabase();
        return db.delete(TABLE_NAME,"rowid = ?", new String[]{rid});
    }
}
