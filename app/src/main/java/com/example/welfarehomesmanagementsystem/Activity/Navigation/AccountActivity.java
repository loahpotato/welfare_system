package com.example.welfarehomesmanagementsystem.Activity.Navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.Activity.LoginActivity;
import com.example.welfarehomesmanagementsystem.Activity.PasswordModifier;
import com.example.welfarehomesmanagementsystem.Activity.ProfileActivity;
import com.example.welfarehomesmanagementsystem.Activity.SignUpActivity;
import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.Entity.User;
import com.example.welfarehomesmanagementsystem.R;

import static android.content.Context.MODE_PRIVATE;

public class AccountActivity extends Fragment {
    private ImageButton profile,addAccount,changePass;
    private Button logout;
    private TextView name,position;
    private SharedPreferences pref ;
    private SharedPreferences.Editor editor;
    private DatabaseHelper DB;
    private User u;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);
        //code here
        pref= getActivity().getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String uid = pref.getString("currentUserId","");
        DB = new DatabaseHelper(getActivity());
        Cursor result = DB.getUserById(uid);
        while(result.moveToNext()) {
            String _id = result.getString(0);
            String _name = result.getString(1);
            String _password = result.getString(2);
            int _position = result.getInt(3);
            int _age = result.getInt(4);
            String _phone = result.getString(5);
            String _gender = result.getString(6);
            u = new User(_id, _name, _password, _position, _age, _phone, _gender);
        }
        name=(TextView)view.findViewById(R.id.account_name);
        name.setText(u.getName());
        position=(TextView)view.findViewById(R.id.account_position);
        if(u.getPosition()==1){
            position.setText("Manager");
        }
        else{
            position.setText("Staff");
        }

        profile = (ImageButton) view.findViewById(R.id.profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ProfileActivity.class);
                startActivity(i);
            }
        });
        addAccount = (ImageButton) view.findViewById(R.id.add_account_button);
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(u.getPosition()==1){
                    Intent i = new Intent(getContext(), SignUpActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getActivity(),"You are not a manager.",Toast.LENGTH_LONG).show();
                }
            }
        });
        logout=(Button)view.findViewById(R.id.Log_out_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = pref.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
        changePass = (ImageButton) view.findViewById(R.id.btnForget);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PasswordModifier.class);
                startActivity(intent);
            }
        });
        return view;
    }
}