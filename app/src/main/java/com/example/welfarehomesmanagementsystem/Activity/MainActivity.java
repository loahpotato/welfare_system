package com.example.welfarehomesmanagementsystem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.welfarehomesmanagementsystem.Activity.Navigation.AccountActivity;
import com.example.welfarehomesmanagementsystem.Activity.Navigation.FunctionsActivity;
import com.example.welfarehomesmanagementsystem.Activity.Navigation.HomeActivity;
import com.example.welfarehomesmanagementsystem.Activity.Navigation.SettingActivity;
import com.example.welfarehomesmanagementsystem.ActivityCollecctor;
import com.example.welfarehomesmanagementsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollecctor.addActivity(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeActivity()).commit();
        BottomNavigationView bottomNavi = findViewById(R.id.navigation_bottom);
        bottomNavi.setOnNavigationItemSelectedListener(naviListener);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollecctor.removeActivity(this);
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