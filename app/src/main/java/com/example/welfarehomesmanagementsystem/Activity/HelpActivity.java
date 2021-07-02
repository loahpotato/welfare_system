package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TitleLayout t = findViewById(R.id.help_title);
        t.setT(R.string.help);
    }
}