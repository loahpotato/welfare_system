package com.example.welfarehomesmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class HealthCheckActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText date;
    TextView chooseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);
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