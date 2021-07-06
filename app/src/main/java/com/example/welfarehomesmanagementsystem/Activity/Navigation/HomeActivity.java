package com.example.welfarehomesmanagementsystem.Activity.Navigation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.ApprovalStatusActivity;
import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.CovidTips;
import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.FirstaidActivity;
import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.HealthResultActivity;
import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.ResidentsRegisterActivity;
import com.example.welfarehomesmanagementsystem.Activity.SignUpActivity;
import com.example.welfarehomesmanagementsystem.Activity.UpdateProfileActivity;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.ViewApprovalActivity;
import com.example.welfarehomesmanagementsystem.widget.MySearchView;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class HomeActivity extends Fragment{
    private ImageView covid;
    private ImageButton health, approval, procure;
    private ImageButton resident;
    private ImageButton firstAid;
    private MySearchView search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,container,false);
        //code here
        search = view.findViewById(R.id.home_search);
        search.getMain_searchview().setQuery("",false);
        search.getMain_searchview().clearFocus();

        covid=view.findViewById(R.id.covid);
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CovidTips.class);
                startActivity(i);
                search.getMain_searchview().setQuery("",false);
                search.getMain_searchview().clearFocus();
            }
        });

        health=(ImageButton) view.findViewById(R.id.health_check_button);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), HealthResultActivity.class);
                startActivity(i);
                search.getMain_searchview().setQuery("",false);
                search.getMain_searchview().clearFocus();
            }
        });

        resident=(ImageButton) view.findViewById(R.id.home_out_record);
        resident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ResidentsRegisterActivity.class);
                startActivity(i);
                search.getMain_searchview().setQuery("",false);
                search.getMain_searchview().clearFocus();
            }
        });

        firstAid = (ImageButton) view.findViewById(R.id.home_ambulance);
        firstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), FirstaidActivity.class);
                startActivity(i);
                search.getMain_searchview().setQuery("",false);
                search.getMain_searchview().clearFocus();
            }
        });

        approval = (ImageButton) view.findViewById(R.id.home_approval);
        approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ApprovalStatusActivity.class);
                startActivity(i);
                search.getMain_searchview().setQuery("",false);
                search.getMain_searchview().clearFocus();
            }
        });

        procure = (ImageButton) view.findViewById(R.id.home_procurement);
        procure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ViewApprovalActivity.class);
                startActivity(i);
                search.getMain_searchview().setQuery("",false);
                search.getMain_searchview().clearFocus();
            }
        });
        return view;
    }

}
