package com.example.welfarehomesmanagementsystem.Activity.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.widget.EditTitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class EditPhone extends AppCompatActivity {

    private EditTitleLayout phone_title;
    private EditText edit_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);
        ActivityCollecctor.addActivity(this);

        phone_title = (EditTitleLayout) findViewById(R.id.phone_title);
        edit_phone = (EditText) findViewById(R.id.et_edit_phone);

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