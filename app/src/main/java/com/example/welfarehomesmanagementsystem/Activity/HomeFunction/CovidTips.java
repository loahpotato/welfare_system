package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.welfarehomesmanagementsystem.widget.TitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class CovidTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_tip);
        TitleLayout t=findViewById(R.id.title_covid);
        t.setT(R.string.covidTips);




    }
}
