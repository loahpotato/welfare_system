package com.example.welfarehomesmanagementsystem.Activity.Navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.Activity.SettingPage.AboutUsActivity;
import com.example.welfarehomesmanagementsystem.Activity.SettingPage.AppVersionActivity;
import com.example.welfarehomesmanagementsystem.Activity.SettingPage.HelpActivity;
import com.example.welfarehomesmanagementsystem.Activity.SettingPage.PolicyActivity;
import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingActivity extends Fragment {
    private TextView about,policy,help,version;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_setting, container, false);
        //code here
        Button exit = (Button) view.findViewById(R.id.Exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = getActivity().getSharedPreferences("CurrentUserId",MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                ActivityCollecctor.finishAll();
            }
        });

        about = view.findViewById(R.id.aboutus);
        policy = view.findViewById(R.id.policy);
        version = view.findViewById(R.id.Version);
        help = view.findViewById(R.id.help);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AboutUsActivity.class);
                startActivity(i);
            }
        });

        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PolicyActivity.class);
                startActivity(i);
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AppVersionActivity.class);
                startActivity(i);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HelpActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    }