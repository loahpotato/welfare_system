package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
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

public class HealthCheckActivity extends AppCompatActivity {
    EditText date,name,age,contact,hospital;
    Button submit;
    TextView chooseDate,chooseHospital;
    private DbHelper_HealthCheck DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check);
        TitleLayout t=findViewById(R.id.title_health_check);
        t.setT(R.string.healthcheck);
        name = findViewById(R.id.patients_name);
        date = findViewById(R.id.appoint_date);
        chooseDate = findViewById(R.id.tv_date);
        age = findViewById(R.id.patients_age);
        contact = findViewById(R.id.contact_number);
        hospital = findViewById(R.id.hospital_name);
        submit = findViewById(R.id.healthcheck_submit);
        DB = new DbHelper_HealthCheck(this);

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDlg();
            }
        });

        chooseHospital = findViewById(R.id.hospi_select);
        chooseHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HealthCheckActivity.this,HospitalListActivity.class);
                startActivityForResult(i,1);
            }
        });

        AddData();

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 3) {
            String result = data.getStringExtra("result");
            hospital.setText(result);
        }
    }

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

    public void showDatePickDlg () {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(HealthCheckActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                HealthCheckActivity.this.date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
        }
