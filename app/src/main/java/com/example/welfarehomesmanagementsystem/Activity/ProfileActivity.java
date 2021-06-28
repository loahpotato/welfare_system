package com.example.welfarehomesmanagementsystem.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.HealthResultActivity;
import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.ItemGroup;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class ProfileActivity extends AppCompatActivity {

    private TextView title_name;
    private ItemGroup id,name,position,gender,age,phone;
    private SharedPreferences pref ;
    private DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TitleLayout t=findViewById(R.id.title_profile);
        t.setT(R.string.profile);

        title_name=findViewById(R.id.profile_name);
        id=findViewById(R.id.ig_id);
        name=findViewById(R.id.ig_name);
        position=findViewById(R.id.ig_position);
        gender=findViewById(R.id.ig_gender);
        age=findViewById(R.id.ig_age);
        phone=findViewById(R.id.ig_phone);

        DB = new DatabaseHelper(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String uid = pref.getString("currentUserId","");

        Cursor result = DB.getUserById(uid);
        while(result.moveToNext()) {
            title_name.setText(result.getString(1));
            id.setContent(uid);
            name.setContent(result.getString(1));
            if(result.getInt(3)==0)
                position.setContent("Staff");
            else
                position.setContent("Manager");
            gender.setContent(result.getString(6));
            age.setContent(result.getString(4));
            phone.setContent(result.getString(5));
        }
        age.getJtRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                startActivityForResult(intent,1);
            }
        });
        phone.getJtRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String re_age = data.getStringExtra("age");
                    String re_phone = data.getStringExtra("phone");
                    if(!re_age.equals(""))
                        age.setContent(re_age);
                    if(!re_phone.equals(""))
                        phone.setContent(re_phone);
                }
                break;
            default:
        }
    }
}