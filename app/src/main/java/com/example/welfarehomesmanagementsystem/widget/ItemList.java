package com.example.welfarehomesmanagementsystem.widget;

import android.app.Activity;
import android.content.Context;
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
    protected TextView name;
    public ItemList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_list,this);

        name = (TextView) findViewById(R.id.health_result_name);
        }

    public void setName(String t) {
        this.name.setText(t);
    }
}
