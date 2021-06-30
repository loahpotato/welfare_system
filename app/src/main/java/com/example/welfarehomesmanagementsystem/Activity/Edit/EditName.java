package com.example.welfarehomesmanagementsystem.Activity.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.DatabaseHelper;

import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.widget.EditTitleLayout;
import com.example.welfarehomesmanagementsystem.R;

public class EditName extends AppCompatActivity {

    private EditTitleLayout tl_title;
    private EditText edit_name;
    private SharedPreferences pref;
    private DatabaseHelper DB;
    private Button btn;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        ActivityCollecctor.addActivity(this);

        tl_title = (EditTitleLayout) findViewById(R.id.tl_title);
        edit_name = (EditText) findViewById(R.id.et_edit_name);
        btn = (Button) findViewById(R.id.edit_name_btn);
        DB = new DatabaseHelper(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        uid = pref.getString("currentUserId","");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB.updateName(uid,edit_name.getText().toString())){
                    Toast.makeText(EditName.this,"Name Edited Successfully",Toast.LENGTH_LONG).show();
                };
            }
        });

        //设置监听器
        //如果点击完成，则更新loginUser并销毁
        tl_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("text", edit_name.getText().toString());
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