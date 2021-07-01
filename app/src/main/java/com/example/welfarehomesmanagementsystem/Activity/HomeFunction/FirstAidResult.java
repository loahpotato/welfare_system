package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.DbHelper_FirstAid;
import com.example.welfarehomesmanagementsystem.R;

public class FirstAidResult extends AppCompatActivity {

    private TextView hName;
    private TextView hPhone;
    private TextView hAddress;
    private TextView hTime;
    private DbHelper_FirstAid DB;
    private SharedPreferences addressPref;
    private String current_address;
    private Cursor result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_result);

        hName = (TextView) findViewById(R.id.firstAid_hospital);
        hPhone = (TextView) findViewById(R.id.hospital_phone);
        hAddress = (TextView) findViewById(R.id.hospital_address);
        hTime = (TextView) findViewById(R.id.hospital_arrTime);
        DB = new DbHelper_FirstAid(this);
        addressPref = getSharedPreferences("CurrentAddress",MODE_PRIVATE);
        current_address = addressPref.getString("CurrentAddress","");
        result = DB.getHospitalByAddress(current_address);

        while(result.moveToNext()){
            hName.setText(result.getString(0));
            hAddress.setText(result.getString(2));
            hPhone.setText(result.getString(1));
            hTime.setText(result.getString(3));
        }


    }
}