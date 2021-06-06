package com.example.welfarehomesmanagementsystem.Activity.Navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.welfarehomesmanagementsystem.Activity.AboutUsActivity;
import com.example.welfarehomesmanagementsystem.Activity.ProfileActivity;
import com.example.welfarehomesmanagementsystem.Activity.SignUpActivity;
import com.example.welfarehomesmanagementsystem.R;

public class AccountActivity extends Fragment {
    private ImageButton profile,addAccount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);
        //code here
        profile = (ImageButton) view.findViewById(R.id.profile_button);
        addAccount = (ImageButton) view.findViewById(R.id.add_account_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ProfileActivity.class);
                startActivity(i);
            }
        });

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SignUpActivity.class);
                startActivity(i);
            }
        });


        return view;
    }
}