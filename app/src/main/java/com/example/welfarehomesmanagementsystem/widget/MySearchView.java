package com.example.welfarehomesmanagementsystem.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.SearchActivity;
import com.example.welfarehomesmanagementsystem.Activity.HomeFunction.ViewApprovalActivity;
import com.example.welfarehomesmanagementsystem.Activity.UpdateProfileActivity;
import com.example.welfarehomesmanagementsystem.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class MySearchView extends LinearLayout {
    private final SearchView main_searchview;

    public MySearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.search_layout,this);
        main_searchview = (SearchView) findViewById(R.id.search_et_input);
        main_searchview.setIconifiedByDefault(false);
        findViews();
    }

    public SearchView getMain_searchview() {
        return main_searchview;
    }

    private void findViews() {
        main_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent i = new Intent(getContext(), SearchActivity.class);
                i.putExtra("Query",query);
                getContext().startActivity(i);
                main_searchview.setQuery("",false);
                main_searchview.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }
}