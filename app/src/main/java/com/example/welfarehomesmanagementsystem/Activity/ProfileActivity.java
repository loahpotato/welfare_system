package com.example.welfarehomesmanagementsystem.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.welfarehomesmanagementsystem.Activity.Edit.EditAddress;
import com.example.welfarehomesmanagementsystem.Activity.Edit.EditName;
import com.example.welfarehomesmanagementsystem.Activity.Edit.EditPhone;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;
import com.example.welfarehomesmanagementsystem.widget.ItemGroup;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import android.content.SharedPreferences;
import com.example.welfarehomesmanagementsystem.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    private ItemGroup ig_name;
    private ItemGroup ig_address;
    private ItemGroup ig_phone;
    private ItemGroup ig_gender;
    private ItemGroup ig_birth;
    private TextView user_name;
    private Cursor result;
    private static final int EDIT_ADDRESS = 1;
    private static final int EDIT_PHONE = 2;
    private static final int EDIT_NAME = 3;
    private OptionsPickerView pvOptions;
    private ArrayList<String> optionsItems_gender = new ArrayList<>();
    private SharedPreferences pref;
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TitleLayout t=findViewById(R.id.title_profile);
        t.setT(R.string.profile);
        initOptionData();
        ig_gender = (ItemGroup)findViewById(R.id.ig_gender);
        ig_birth = (ItemGroup)findViewById(R.id.ig_birth);
        ig_name = (ItemGroup)findViewById(R.id.ig_name);
        ig_address = (ItemGroup)findViewById(R.id.ig_address);
        ig_phone = (ItemGroup)findViewById(R.id.ig_phone);
        user_name = (TextView)findViewById(R.id.user_name);

        DB = new DatabaseHelper(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String uid = pref.getString("currentUserId","");
        result = DB.getUserById(uid);

        while(result.moveToNext()) {
            ig_name.getContentEdt().setText(result.getString(1));
        }
        ig_gender.getJtRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGender();
            }
        });
       ig_birth.getJtRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBirth();
            }
        });
       ig_name.getJtRightIv().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent  = new Intent(ProfileActivity.this, EditName.class);
               intent.putExtra("text", ig_name.getContentEdt().getText().toString());
               startActivityForResult(intent, EDIT_NAME);
           }
       });
       ig_address.getJtRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ProfileActivity.this, EditAddress.class);
                intent.putExtra("text", ig_address.getContentEdt().getText().toString());
                startActivityForResult(intent, EDIT_ADDRESS);
            }
        });
        ig_phone.getJtRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ProfileActivity.this, EditPhone.class);
                intent.putExtra("text", ig_phone.getContentEdt().getText().toString());
                startActivityForResult(intent, EDIT_PHONE);
            }
        });

    }

    private void initOptionData() {
        optionsItems_gender.add("Male");
        optionsItems_gender.add("Female");
    }

    private  void showGender(){
            OptionsPickerView pvOptions = new OptionsPickerBuilder(ProfileActivity.this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    String tx = optionsItems_gender.get(options1);
                    ig_gender.getContentEdt().setText(tx);
                }
            })
                    .setSubmitText("Confirm")
                    .setCancelText("Cancel")
                    .setSubmitColor(Color.WHITE)
                    .setCancelColor(Color.WHITE)
                    .build();//创建
            pvOptions.setPicker(optionsItems_gender);
            pvOptions.show();
        }

   private  void showBirth(){
       Calendar selectedDate = Calendar.getInstance();
       TimePickerView pvTime = new TimePickerBuilder(ProfileActivity.this, new OnTimeSelectListener() {
           @Override
           public void onTimeSelect(Date date, View v) {
               //选择了则显示并暂存LoginUser，退出时在保存至数据库
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
               ig_birth.getContentEdt().setText(sdf.format(date));
           }
       })      .setDate(selectedDate)
               .setSubmitText("Confirm")
               .setCancelText("Cancel")
               .setSubmitColor(Color.WHITE)
               .setCancelColor(Color.WHITE)
               .build();
       pvTime.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        switch (requestCode){
            case EDIT_NAME:
                if(resultCode == RESULT_OK){
                    ig_name.getContentEdt().setText(data.getStringExtra("text"));
                    user_name.setText(data.getStringExtra("text"));
                }
                break;
            case EDIT_ADDRESS:
                if(resultCode == RESULT_OK){
                    ig_address.getContentEdt().setText(data.getStringExtra("text"));
                }
                break;
            case EDIT_PHONE:
                if(resultCode == RESULT_OK){
                    ig_phone.getContentEdt().setText(data.getStringExtra("text"));
                }
                break;
            default:
                break;
        }
    }

}