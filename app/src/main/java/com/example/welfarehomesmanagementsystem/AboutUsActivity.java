package com.example.welfarehomesmanagementsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.Layout.TitleLayout;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        TitleLayout t=findViewById(R.id.aboutus_title);
        t.setT(R.string.aboutUs);
        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null)
            actionbar.hide();

        TextView contactNum = (TextView) findViewById(R.id.contactnum);
        contactNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + contactNum.getText().toString()));
                startActivity(i);
            }
        });
    }
}