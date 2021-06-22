package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.welfarehomesmanagementsystem.Activity.MainActivity;
import com.example.welfarehomesmanagementsystem.Activity.SignUpActivity;
import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.DbHelper_HealthCheck;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

import java.util.Calendar;

public class HealthCheckActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText date,name,age,contact,hospital;
    Button submit;
    TextView chooseDate;
    private DbHelper_HealthCheck DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);
        TitleLayout t=findViewById(R.id.title_health_check);
        t.setT(R.string.healthcheck);
        name = findViewById(R.id.patients_name);
        date = findViewById(R.id.appoint_date);
        age = findViewById(R.id.patients_age);
        contact = findViewById(R.id.contact_number);
        hospital = findViewById(R.id.hospital_addr);
        submit = findViewById(R.id.healthcheck_submit);
        DB = new DbHelper_HealthCheck(this);
        AddData();

    };
    public void AddData(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1,age1,contact1,hospital1,date1;
                name1=name.getText().toString(); age1=age.getText().toString();contact1=contact.getText().toString();hospital1=hospital.getText().toString();date1=date.getText().toString();

                if (name1.equals("")||age1.equals("")||contact1.equals("")||hospital1.equals("")||date1.equals("")) {
                    Toast.makeText(HealthCheckActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean isInserted = DB.insertData(name.getText().toString(),date.getText().toString(),age.getText().toString(),contact.getText().toString(),hospital.getText().toString());
                    if(isInserted == true)
                        Toast.makeText(HealthCheckActivity.this,"Make appoint successfully!",Toast.LENGTH_SHORT).show();
                        else
                        Toast.makeText(HealthCheckActivity.this,"Fail!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker dp,int year,int month,int day){
        String desc = String.format("Choose a date%dYear%dMonth%dDay",year,month+1,day);
        chooseDate = findViewById(R.id.tv_date);
        date = findViewById(R.id.appoint_date);
        date.setText(desc);
        if(chooseDate.getId()==R.id.tv_date){
            //获取实例，包含当前年月日
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this,this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MARCH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }

    }
        }
