package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.R;

public class LoginActivity extends AppCompatActivity {
    private EditText user,pass;
    private Button signIn;
    private Button change;
    private DatabaseHelper DB;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCollecctor.addActivity(this);
        user = findViewById(R.id.username1);
        pass = findViewById(R.id.password1);
        signIn = findViewById(R.id.btnSignIn1);
        change = findViewById(R.id.btnChange);
        DB = new DatabaseHelper(this);
        DB.getReadableDatabase();
        pref=getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        editor = pref.edit();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = user.getText().toString();
                String password = pass.getText().toString();

                if (userId.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                }else{
                    boolean checkUserPass = DB.checkPassword(userId,password);
                    if(checkUserPass){
                        Toast.makeText(LoginActivity.this, "Sign in Successfully",Toast.LENGTH_LONG).show();
                        editor.putString("currentUserId",userId);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        editor.clear();
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                    }
                    editor.apply();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordModifier.class);
                startActivity(intent);
            }
        });

    }
}