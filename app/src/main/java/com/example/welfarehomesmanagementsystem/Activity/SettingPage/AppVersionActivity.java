package com.example.welfarehomesmanagementsystem.Activity.SettingPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class AppVersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_version);
        TitleLayout t = findViewById(R.id.version_title);
        t.setT(R.string.appversion);
    }
}