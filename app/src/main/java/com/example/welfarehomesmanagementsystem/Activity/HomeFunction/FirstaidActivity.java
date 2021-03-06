package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import com.example.welfarehomesmanagementsystem.Activity.SignUpActivity;
import com.example.welfarehomesmanagementsystem.DbHelper_FirstAid;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

import java.util.ArrayList;

public class FirstaidActivity extends AppCompatActivity {

    private TextView address;
    private TextView symptom;
    private TextView num;
    private Button btn;
    private ArrayList<String> optionsItems_address = new ArrayList<>();
    private ArrayList<String> optionsItems_symptom = new ArrayList<>();
    private ArrayList<String> optionsItems_num = new ArrayList<>();
    private DbHelper_FirstAid DB;
    private SharedPreferences addressPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);
        TitleLayout t=findViewById(R.id.title_firstAid);
        t.setT(R.string.ambulance);

        address = (TextView) findViewById(R.id.firstAid_address);
        symptom = (TextView) findViewById(R.id.firstAid_symptom);
        num = (TextView) findViewById(R.id.firstAid_people);
        btn = (Button) findViewById(R.id.firstAid_btn);
        DB = new DbHelper_FirstAid(this);
        DB.getReadableDatabase();
        addressPref = getSharedPreferences("CurrentAddress",MODE_PRIVATE);
        editor = addressPref.edit();


        initOptionData();

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(FirstaidActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String tx = optionsItems_address.get(options1);
                        address.setText(tx);
                        editor.putString("CurrentAddress",tx);
                        editor.apply();
                    }
                })
                        .setSubmitText("Confirm")
                        .setCancelText("Cancel")
                        .setSubmitColor(Color.WHITE)
                        .setCancelColor(Color.WHITE)
                        .build();
                pvOptions.setPicker(optionsItems_address);
                pvOptions.show();
            }
        });

        symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(FirstaidActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String tx = optionsItems_symptom.get(options1);
                        symptom.setText(tx);
                    }
                })
                        .setSubmitText("Confirm")
                        .setCancelText("Cancel")
                        .setSubmitColor(Color.WHITE)
                        .setCancelColor(Color.WHITE)
                        .build();
                pvOptions.setPicker(optionsItems_symptom);
                pvOptions.show();
            }
        });

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(FirstaidActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String tx = optionsItems_num.get(options1);
                        num.setText(tx);
                    }
                })
                        .setSubmitText("Confirm")
                        .setCancelText("Cancel")
                        .setSubmitColor(Color.WHITE)
                        .setCancelColor(Color.WHITE)
                        .build();
                pvOptions.setPicker(optionsItems_num);
                pvOptions.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(address.getText().toString().equals("") || symptom.getText().toString().equals("") || num.getText().toString().equals("") ){
                    Toast.makeText(FirstaidActivity.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i = new Intent(FirstaidActivity.this,FirstAidResult.class);
                    startActivity(i);
                }
            }
        });

    }

    private void initOptionData(){
        optionsItems_address.add("ZN District");
        optionsItems_address.add("Tql District");
        optionsItems_address.add("Yjdy District");
        optionsItems_address.add("Yyds District");
        optionsItems_address.add("Scc District");
        optionsItems_address.add("Yang District");

        optionsItems_symptom.add("Shock");
        optionsItems_symptom.add("Hemorrhoea");
        optionsItems_symptom.add("Serious Injury");
        optionsItems_symptom.add("Drowning");
        optionsItems_symptom.add("Burn");

        optionsItems_num.add("1");
        optionsItems_num.add("2");
        optionsItems_num.add("3");
        optionsItems_num.add("4");


        DB.insertData("ZN Hospital","7758258","ZN Street","10min","ZN District");
        DB.insertData("Tql Hospital","5864588","Tql Street","12min","Tql District");
        DB.insertData("Yjdy Hospital","6837994","Yjdy Street","17min","Yjdy District");
        DB.insertData("Yyds Hospital","3860374","Yyds Street","25min","Yyds District");
        DB.insertData("Scc Hospital","2967024","Sccc Street","6min","Scc District");
        DB.insertData("Yang Hospital","6395206","Liu Street","2min","Yang District");
    }
}