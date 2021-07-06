package com.example.welfarehomesmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper_FirstAid extends SQLiteOpenHelper {

    public static final String DBNAME = "FirstAid.db";

    public DbHelper_FirstAid(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE HOSPITAL(hname TEXT primary key, hphone TEXT, haddress TEXT, arrtime TEXT, address TEXT)");
    }

    //Drop the table if exists
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS HOSPITAL");
        onCreate(MyDB);
    }

    @Override
    public void onDowngrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        //Drop table if it's already exist
        MyDB.execSQL("DROP TABLE IF EXISTS HOSPITAL" );
        //if not exist, we can parse the onCreate method to run
        onCreate(MyDB);
    }

    public void insertData(String hName, String hPhone, String hAddress,String arrTime, String address){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hname", hName);
        contentValues.put("hphone", hPhone);
        contentValues.put("haddress",hAddress);
        contentValues.put("arrtime",arrTime);
        contentValues.put("address",address);
        MyDB.insert("HOSPITAL",null,contentValues);
    }

    public Cursor getHospitalByAddress(String address){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM HOSPITAL WHERE address = ?",  new String[]{address});
        return result;
    }
}
