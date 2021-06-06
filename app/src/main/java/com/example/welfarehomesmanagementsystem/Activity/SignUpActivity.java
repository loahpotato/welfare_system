package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;
import com.example.welfarehomesmanagementsystem.ActivityCollecctor;

public class SignUpActivity extends AppCompatActivity {
    private EditText username, password, rePassword;
    private Button signIn, signUp;
    private com.example.welfarehomesmanagementsystem.DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TitleLayout t=findViewById(R.id.title_sign_up);
        t.setT(R.string.sign_up);

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
                                AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);
                                dialog.setTitle(R.string.sign_up);
                                if(insert){
                                    dialog.setMessage("Registration Successfully");
                                    dialog.setCancelable(false);
                                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            username.setText("");
                                            password.setText("");
                                            rePassword.setText("");
                                        }
                                    });
                                    dialog.show();
                                }else{
                                    Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    dialog.setMessage("Registration Failed, please try again.");
                                    dialog.setCancelable(false);
                                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            username.setText("");
                                            password.setText("");
                                            rePassword.setText("");
                                        }
                                    });
                                    dialog.show();
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
                AlertDialog.Builder dialog_switch = new AlertDialog.Builder(SignUpActivity.this);
                dialog_switch.setTitle("Switch account");
                dialog_switch.setMessage("Confirm logout from current account.");
                dialog_switch.setCancelable(false);
                dialog_switch.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCollecctor.finishAll();
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                dialog_switch.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        username.setText("");
                        password.setText("");
                        rePassword.setText("");
                    }
                });
                dialog_switch.show();
            }
        });
    }
}