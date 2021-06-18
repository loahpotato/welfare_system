package com.example.welfarehomesmanagementsystem.Activity.Navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.HealthCheckActivity;
import com.example.welfarehomesmanagementsystem.R;

public class FunctionsActivity extends Fragment {
    private LinearLayout healthcheck;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_functions, container, false);
        //code here
        healthcheck=(LinearLayout) view.findViewById(R.id.function_health_check_button);
        healthcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), HealthCheckActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}