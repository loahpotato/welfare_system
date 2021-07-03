package com.example.welfarehomesmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper_HealthCheck extends SQLiteOpenHelper {
    //Declare variable name for database, table and column
    public static final String DATABASE_NAME = "HealthCheckAppointment.db";
    public static final String TABLE_NAME = "HealthCheckAppointment_table";
    public static final String COLUMN_1 = "NAME";
    public static final String COLUMN_2 = "DATE";
    public static final String COLUMN_3 = "AGE";
    public static final String COLUMN_4 = "CONTACT";
    public static final String COLUMN_5 = "HOSPITAL";
    public static final String COLUMN_6 = "STAFF";
    public DbHelper_HealthCheck(@Nullable Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(NAME TEXT, DATE TEXT, AGE TEXT,CONTACT TEXT,HOSPITAL TEXT, STAFF TEXT)");
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
    public boolean insertData(String name,String date, String age,String contact, String hospital, String staff){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,name);
        contentValues.put(COLUMN_2,date);
        contentValues.put(COLUMN_3,age);
        contentValues.put(COLUMN_4,contact);
        contentValues.put(COLUMN_5,hospital);
        contentValues.put(COLUMN_6,staff);
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

    public boolean checkRepeat(String name, String date, String hospital){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT *  FROM "  + TABLE_NAME + " WHERE NAME = ?",  new String[]{name});
        while(result.moveToNext()){
            if(result.getString(1).equals(date) && result.getString(4).equals(hospital))
                return false;
        }
        return true;
    }

    public Cursor getByRowId(String rid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rowid = ? ",new String[]{rid});
        return result;
    }

    public Integer deleteData(String rid, String name){
        SQLiteDatabase db =getWritableDatabase();
        return db.delete(TABLE_NAME,"STAFF = ? AND NAME = ?" , new String[]{rid, name});
    }
}
