package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class UpdateProfileActivity extends AppCompatActivity {
    private EditText age, phone;
    private Button confirm;
    private SharedPreferences pref ;
    private DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        TitleLayout t=findViewById(R.id.title_update_profile);
        t.setT(R.string.update_profile);

        DB = new DatabaseHelper(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String uid = pref.getString("currentUserId","");
        age=findViewById(R.id.update_age);
        phone=findViewById(R.id.update_phone);
        confirm=findViewById(R.id.btnUpdate);
        AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateProfileActivity.this);
        dialog.setTitle("Update Profile");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!age.getText().toString().equals("") || !phone.getText().toString().equals("") ){
                    DB.updateProfile(uid, age.getText().toString(),phone.getText().toString());
                    dialog.setMessage("Update Successfully");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra("age",age.getText().toString());
                            intent.putExtra("phone",phone.getText().toString());
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    });
                    dialog.show();
                }
                else{
                    Toast.makeText(UpdateProfileActivity.this,"Please enter at least one field",Toast.LENGTH_LONG).show();
                }
            }
        });

        View focus = findViewById(R.id.profile_view);
        focus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(null != UpdateProfileActivity.this.getCurrentFocus()){
                    /**
                     * 点击空白位置 隐藏软键盘
                     */
                    InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    return mInputMethodManager.hideSoftInputFromWindow(UpdateProfileActivity.this.getCurrentFocus().getWindowToken(), 0);
                }
                return false;
            }
        });
    }
}