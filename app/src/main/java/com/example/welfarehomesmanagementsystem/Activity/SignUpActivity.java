package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText username, password, rePassword;
    private Button signIn, signUp;
    private com.example.welfarehomesmanagementsystem.DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.rePassword);
        signIn = findViewById(R.id.btnSignIn);
        signUp = findViewById(R.id.btnSignUp);
        DB = new com.example.welfarehomesmanagementsystem.DatabaseHelper(this);
        //Sign Up function
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repeat = rePassword.getText().toString();

                if(user.equals("") || pass.equals("") || repeat.equals("")){
                    Toast.makeText(SignUpActivity.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                }
                else{
                    if (pass.equals(repeat)){
                        boolean checkUser;
                        checkUser = DB.checkUsername(user);
                        if(!checkUser){

                            boolean regulation = DB.isContainAll(pass);
                            if(regulation){
                                boolean insert = DB.insertData(user,pass);
                                if(insert){
                                    Toast.makeText(SignUpActivity.this,"Register Successfully",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(SignUpActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                }
                            }else{
                                Toast.makeText(SignUpActivity.this, "The password doesn't conform to the specification", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(SignUpActivity.this,"User already exists",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //Sign In function
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}