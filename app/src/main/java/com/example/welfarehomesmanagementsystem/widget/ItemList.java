package com.example.welfarehomesmanagementsystem.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.example.welfarehomesmanagementsystem.R;

public class ItemList extends LinearLayout {
    protected TextView name,date,hospital;
    protected LinearLayout round;
    public ItemList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_list,this);

        name = (TextView) findViewById(R.id.health_result_name);
        date = (TextView) findViewById(R.id.health_result_date);
        hospital = (TextView) findViewById(R.id.health_result_hospital);
        round = findViewById(R.id.roundLine);
        }
    public void setLine(int width,int color){
        GradientDrawable myGrad = (GradientDrawable)round.getBackground();
        myGrad.setStroke(width,color);
    }
    public void setName(String t) {
        this.name.setText(t);
    }
    public void setDate(String t) {
        this.date.setText(t);
    }
    public void setHospital(String t) {
        this.hospital.setText(t);
    }
}
