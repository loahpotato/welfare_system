package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.welfarehomesmanagementsystem.Activity.SignUpActivity;
import com.example.welfarehomesmanagementsystem.Entity.HealthCheck;
import com.example.welfarehomesmanagementsystem.Entity.ResidentsRegister;
import com.example.welfarehomesmanagementsystem.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ListView list;
    private final String[] searchResult = {"Health check appointment","Health check appointment records ",
            "Procurement request","Resident register","View approval status","Add new employee"};
    List<String> SearchItem=  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        list = findViewById(R.id.search_lv_tips);

        TextView searchNote = findViewById(R.id.search_note);

        Collections.addAll(SearchItem, searchResult);
        Intent i = getIntent();
        String q = i.getStringExtra("Query");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchResult);
        list.setAdapter(adapter);
        adapter.getFilter().filter(q);
        list.setEmptyView(searchNote);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = adapter.getItem(position);
                if(s.equals(searchResult[0])){
                    Intent i = new Intent(SearchActivity.this, HealthCheckActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(s.equals(searchResult[1])){
                    Intent i = new Intent(SearchActivity.this, HealthResultActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(s.equals(searchResult[2])){
                    Intent i = new Intent(SearchActivity.this, ProcurementActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(s.equals(searchResult[3])){
                    Intent i = new Intent(SearchActivity.this, ResidentsRegisterActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(s.equals(searchResult[4])){
                    Intent i = new Intent(SearchActivity.this, ApprovalStatusActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(s.equals(searchResult[5])){
                    Intent i = new Intent(SearchActivity.this, SignUpActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        });

        }


}
