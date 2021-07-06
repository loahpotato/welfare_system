package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

    @SuppressLint("SetTextI18n")
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
            String _id = result.getString(0);
            String _name = result.getString(1);
            String _date = result.getString(2);
            String _age = result.getString(3);
            String _contact = result.getString(4);
            String _hospital = result.getString(5);
            String _staff = result.getString(6);
            HealthCheck h =new HealthCheck(_id,_name,_date,_age,_contact,_hospital,_staff);
            healthList.add(h);
        }

        LinearLayout health = (LinearLayout) findViewById(R.id.layout_health_result);

        if(healthList.isEmpty()){
            TextView note = findViewById(R.id.health_chek_note);
            note.setVisibility(View.VISIBLE);
            note.setText("No health check appointment record.");
        }
        else{
            for (HealthCheck h : healthList) {
                ItemList showResult=new ItemList(this,null);
                showResult.setTitleContent(h.getName());
                showResult.setInfoContent1(h.getDate());
                showResult.setInfoContent2(h.getHospital());
                showResult.setLine(3,0xFF0F9EB1);
                showResult.setTitleColor(0xFF0F9EB1);
                health.addView(showResult);

                showResult.setNote("Delete");
                showResult.getNote_bg().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int deleted = DB.deleteData(h.getId(),h.getHospital(),h.getDate());
                        if(deleted == 1){
                            Toast.makeText(HealthResultActivity.this,"Appointment is deleted",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(HealthResultActivity.this,"No appointment",Toast.LENGTH_SHORT).show();
                        }
                        Intent i = new Intent(HealthResultActivity.this,HealthResultActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        }
    }
}
