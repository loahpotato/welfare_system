package com.example.welfarehomesmanagementsystem.Activity.SettingPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.welfarehomesmanagementsystem.widget.TitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class PolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        TitleLayout t=findViewById(R.id.policy_title);
        t.setT(R.string.policy);
    }
}