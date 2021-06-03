package com.example.welfarehomesmanagementsystem.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.welfarehomesmanagementsystem.HomeFunction.CovidTips;
import com.example.welfarehomesmanagementsystem.R;


public class HomeActivity extends Fragment{
    private ImageView covid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,container,false);
        //code here
        covid=view.findViewById(R.id.covid);
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CovidTips.class);
                startActivity(i);
            }
        });


        return view;
    }

}