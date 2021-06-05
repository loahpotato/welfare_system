package com.example.welfarehomesmanagementsystem.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TitleLayout t=findViewById(R.id.title_profile);
        t.setT(R.string.profile);
    }
}