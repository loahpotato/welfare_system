package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.welfarehomesmanagementsystem.DbHelper_HealthCheck;
import com.example.welfarehomesmanagementsystem.Entity.HealthCheck;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.ItemList;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

public class HealthResultActivity extends AppCompatActivity {

    List<HealthCheck> healthList = new ArrayList<HealthCheck>();
    private DbHelper_HealthCheck DB;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_result);
        TitleLayout t = findViewById(R.id.title_health_result);
        t.setT(R.string.healthresult);

        DB = new DbHelper_HealthCheck(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String currentUid= pref.getString("currentUserId","");
        Cursor result=DB.getDataByUser(currentUid);
        while(result.moveToNext()){
            String _name = result.getString(0);
            String _date = result.getString(1);
            String _age = result.getString(2);
            String _contact = result.getString(3);
            String _hospital = result.getString(4);
            String _staff = result.getString(5);
            HealthCheck h =new HealthCheck(_name,_date,_age,_contact,_hospital,_staff);
            healthList.add(h);
        }
        LinearLayout health = (LinearLayout) findViewById(R.id.layout_health_result);

        for (HealthCheck h : healthList) {
            ItemList showResult=new ItemList(this,null);
            showResult.setName(h.getName());
            health.addView(showResult);
        }



    }
}