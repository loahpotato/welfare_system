package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class HospitalListActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ib1,ib2,ib3,ib4,ib5,ib6;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    EditText et;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        TitleLayout t = findViewById(R.id.hospital_list_title);
        t.setT(R.string.hospital_list);
        ib1 = findViewById(R.id.hospital1_btn);
        ib2 = findViewById(R.id.hospital2_btn);
        ib3 = findViewById(R.id.hospital3_btn);
        ib4 = findViewById(R.id.hospital4_btn);
        ib5 = findViewById(R.id.hospital5_btn);
        ib6 = findViewById(R.id.hospital6_btn);

        tv1 = findViewById(R.id.hospital1_tel);
        tv2 = findViewById(R.id.hospital2_tel);
        tv3 = findViewById(R.id.hospital3_tel);
        tv4 = findViewById(R.id.hospital4_tel);
        tv5 = findViewById(R.id.hospital5_tel);
        tv6 = findViewById(R.id.hospital6_tel);

        et = findViewById(R.id.hospital_name);

        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
        ib4.setOnClickListener(this);
        ib5.setOnClickListener(this);
        ib6.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hospital1_btn:
                message = "Zn hospital";
                break;
            case R.id.hospital2_btn:
                message="Tql hospital";
                break;
            case R.id.hospital3_btn:
                message="Yjdy hospital";
                break;
            case R.id.hospital4_btn:
                message="Yzdy hospital";
                break;
            case R.id.hospital5_btn:
                message="Scc hospital";
                break;
            case R.id.hospital6_btn:
                message="Yang hospital";
                break;
        }
        Intent i = new Intent();
        i.putExtra("result",message);
        setResult(3,i);
        finish();
    }
}