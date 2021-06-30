package com.example.welfarehomesmanagementsystem.Activity.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.DatabaseHelper;

import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.widget.EditTitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class EditPhone extends AppCompatActivity {

    private EditTitleLayout phone_title;
    private EditText edit_phone;
    private SharedPreferences pref;
    private DatabaseHelper DB;
    private Button btn;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);
        ActivityCollecctor.addActivity(this);

        phone_title = (EditTitleLayout) findViewById(R.id.phone_title);
        edit_phone = (EditText) findViewById(R.id.et_edit_phone);
        btn = (Button) findViewById(R.id.edit_phone_btn);
        DB = new DatabaseHelper(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        uid = pref.getString("currentUserId","");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB.updatePhone(uid,edit_phone.getText().toString())){
                    Toast.makeText(EditPhone.this,"Phone Edited Successfully",Toast.LENGTH_LONG).show();
                };
            }
        });
        //设置监听器
        //如果点击完成，则更新loginUser并销毁
        phone_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("text", edit_phone.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}