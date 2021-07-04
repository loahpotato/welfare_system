package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.welfarehomesmanagementsystem.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        LinearLayout welcomeImg = (LinearLayout) findViewById(R.id.welcome);//启动时显示的图片
        AlphaAnimation anima = new AlphaAnimation(0.5f, 1.0f);
        AlphaAnimation animaStay = new AlphaAnimation(1,1);
        animaStay.setDuration(1000);
        anima.setDuration(2000);// 设置动画显示时间
        welcomeImg.startAnimation(anima);//设置启动时图片的动画
        anima.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                welcomeImg.startAnimation(animaStay);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animaStay.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);

                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}