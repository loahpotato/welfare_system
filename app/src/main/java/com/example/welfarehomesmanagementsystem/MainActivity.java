package com.example.welfarehomesmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null)
            actionbar.hide();

        BottomNavigationView bottomNavi = findViewById(R.id.navigation_bottom);
        bottomNavi.setOnNavigationItemSelectedListener(naviListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener naviListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.navi_home:
                    fragment = new HomeActivity();
                    break;
                case R.id.navi_functions:
                    fragment = new FunctionsActivity();
                    break;
                case R.id.navi_account:
                    fragment = new AccountActivity();
                    break;
                case R.id.navi_settings:
                    fragment = new SettingActivity();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };
}