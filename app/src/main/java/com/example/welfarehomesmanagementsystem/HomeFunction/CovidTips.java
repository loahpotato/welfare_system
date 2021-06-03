package com.example.welfarehomesmanagementsystem.HomeFunction;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.welfarehomesmanagementsystem.Layout.TitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class CovidTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_tip);
        TitleLayout t=findViewById(R.id.title_covid);
        t.setT(R.string.covidTips);
        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null)
            actionbar.hide();



    }
}
