package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class PasswordModifier extends AppCompatActivity {
    private EditText userId, old_password, new_password, rePassword;
    private Button signIn;
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modifier);
        TitleLayout t=findViewById(R.id.title_password_change);
        t.setT(R.string.password_change);

        userId = findViewById(R.id.userId2);
        old_password = findViewById(R.id.old_password);
        new_password = findViewById(R.id.new_password);
        rePassword = findViewById(R.id.repeat_new_password);
        signIn = findViewById(R.id.btnSignIn2);
        DB = new DatabaseHelper(this);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userId.getText().toString();
                String oldpass = old_password.getText().toString();
                String newpass = new_password.getText().toString();
                String repeat = rePassword.getText().toString();

                if(user.equals("") || oldpass.equals("") || newpass.equals("") || repeat.equals("")){
                    Toast.makeText(PasswordModifier.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                }
                else{
                    if (newpass.equals(oldpass))
                        Toast.makeText(PasswordModifier.this,"New Password mustn't be same as Old Password !",Toast.LENGTH_LONG).show();
                    else{
                        if (newpass.equals(repeat)){
                            boolean checkUser = DB.checkUserId(user);
                            if(checkUser){
                                boolean checkUserPass = DB.checkPassword(user,oldpass);
                                if(checkUserPass){
                                    boolean regulation = DB.isContainAll(newpass);
                                    if(regulation){
                                        boolean update = DB.update(user,newpass);
                                        if(update){
                                            Toast.makeText(PasswordModifier.this,"Password Modification Successfully",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(PasswordModifier.this, "Password modification failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(PasswordModifier.this, "The password doesn't conform to the specification", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(PasswordModifier.this,"Old Password is wrong.",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(PasswordModifier.this,"This Staff doesn't exist.",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(PasswordModifier.this, "Password not matching", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}