package com.example.welfarehomesmanagementsystem.widget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.R;

public class ItemDetail extends LinearLayout {
    protected TextView title,content;
    protected LinearLayout item;

    public ItemDetail(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_detail,this);

        item=findViewById(R.id.detail_info);
        title=findViewById(R.id.detail_title);
        content=findViewById(R.id.detail_content);
    }

    public void setItem(String title, String content){
        this.title.setText(title);
        this.content.setText(content);
    }
}