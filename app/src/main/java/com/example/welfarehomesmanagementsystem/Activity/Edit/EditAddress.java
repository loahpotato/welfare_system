package com.example.welfarehomesmanagementsystem.Activity.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.widget.EditTitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class EditAddress extends AppCompatActivity {

    private EditTitleLayout address_title;
    private EditText edit_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ActivityCollecctor.addActivity(this);

        address_title = (EditTitleLayout) findViewById(R.id.address_title);
        edit_address = (EditText) findViewById(R.id.et_edit_address);

        //设置监听器
        //如果点击完成，则更新loginUser并销毁
        address_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("text", edit_address.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollecctor.removeActivity(this);
    }
}