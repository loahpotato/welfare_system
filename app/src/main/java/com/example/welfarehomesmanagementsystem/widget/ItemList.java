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
    protected TextView title,name,infoContent1,infoContent2,info1, info2,note;
    protected LinearLayout round, note_bg;
    public ItemList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_list,this);

        title=findViewById(R.id.list_title);
        name = (TextView) findViewById(R.id.list_title_content);
        info1 = findViewById(R.id.list_info1);
        infoContent1=(TextView) findViewById(R.id.list_info1_content);
        info2 = (TextView) findViewById(R.id.list_info2);
        infoContent2=(TextView) findViewById(R.id.list_info2_content);
        round = findViewById(R.id.roundLine);
        note=findViewById(R.id.list_note);
        note_bg=findViewById(R.id.list_note_bg);
        }
    public void setLine(int width,int color){
        GradientDrawable myGrad = (GradientDrawable)round.getBackground();
        myGrad.setStroke(width,color);
    }
    public void setNote(String t) {
        this.note_bg.setVisibility(VISIBLE);
        this.note.setText(t);
    }
    public void setTitle(String t) {
        this.title.setText(t);
    }
    public void setTitleContent(String t) {
        this.name.setText(t);
    }
    public void setTitleColor(int c) {
        GradientDrawable myGrad = (GradientDrawable)note_bg.getBackground();
        myGrad.setColor(c);
        this.name.setTextColor(c);
        this.title.setTextColor(c);
    }
    public void setInfo1(String t) {
        this.infoContent1.setText(t);
    }
    public void setInfo2(String t) {
        this.infoContent2.setText(t);
    }
    public void setInfoContent1(String t) {
        this.info1.setText(t);
    }
    public void setInfoContent2(String t) {
        this.info2.setText(t);
    }
}
