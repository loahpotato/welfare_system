package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.welfarehomesmanagementsystem.Activity.ApprovalFinish;
import com.example.welfarehomesmanagementsystem.Activity.ApprovalWait;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class ApprovalStatusActivity extends AppCompatActivity {
    private final List<Fragment> mFragment = new ArrayList<>();//fragments
    private final List<String> mTitleList = new ArrayList<String>();//tab titles
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_status);
        TitleLayout t = findViewById(R.id.title_approval_status);
        t.setT(R.string.approalStatus);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_view);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mFragment.add(ApprovalWait.newInstance());
        mFragment.add(ApprovalFinish.newInstance());

        //add titles
        mTitleList.add("Waiting");
        mTitleList.add("Finished");

        //add fragments
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }

        });

        //set relation between TabLayout and ViewPager
        mTabLayout.setupWithViewPager(mViewPager);

    }


}