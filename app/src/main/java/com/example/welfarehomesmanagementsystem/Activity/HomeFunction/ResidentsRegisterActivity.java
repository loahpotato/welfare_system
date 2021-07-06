package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.welfarehomesmanagementsystem.DbHelper_ResidentsRegister;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

import java.util.Calendar;

public class ResidentsRegisterActivity extends AppCompatActivity {
    private EditText id, name,date,age,relative, contact, note;
    private Button submit;
    private TextView resident_chooseDate;
    String currentUid;
    private String gender;
    private RadioGroup radioGroup;
    private RadioButton man,woman;
    private DbHelper_ResidentsRegister DB;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residents_register);
        TitleLayout t=findViewById(R.id.title_residents_register);
        t.setT(R.string.resident_register);

        id = findViewById(R.id.resident_id);
        name = findViewById(R.id.resident_name);
        date = findViewById(R.id.resident_date);
        resident_chooseDate = findViewById(R.id.resident_chooseDate);
        man = findViewById(R.id.resident_man);
        woman = findViewById(R.id.resident_woman);
        age = findViewById(R.id.resident_age);
        relative = findViewById(R.id.resident_relative);
        contact = findViewById(R.id.resident_contact);
        note = findViewById(R.id.resident_note);
        submit = findViewById(R.id.resident_submit);
        DB = new DbHelper_ResidentsRegister(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        currentUid= pref.getString("currentUserId","");

        resident_chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDlg();
            }
        });
        radioGroup=findViewById(R.id.resident_gender);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (man.getId() == checkedId) {
                    gender = (String) man.getText();
                }
                if (woman.getId() == checkedId) {
                    gender = (String) woman.getText();
                }
            }
        });

        AddData();

    };

    //Make an appointment, call Dbhelper to insert data into DB
    public void AddData(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _id,_name,_date,_age,_relative, _contact, _note;
                _id=id.getText().toString();
                _name=name.getText().toString();
                _date=date.getText().toString();
                _age=age.getText().toString();
                _relative=relative.getText().toString();
                _contact=contact.getText().toString();
                _note=note.getText().toString();

                AlertDialog.Builder dialog = new AlertDialog.Builder(ResidentsRegisterActivity.this);
                dialog.setTitle("Resident Register");
                if (_name.equals("")||_date.equals("")||_age.equals("")||_relative.equals("")||_contact.equals("")) {
                    Toast.makeText(ResidentsRegisterActivity.this, "Please enter necessary fields", Toast.LENGTH_LONG).show();
                }
                else {
                    if(DB.checkRepeat(_id) ){
                        boolean isInserted = DB.insertData(_id, _name, _date, gender, _age,_relative, _contact, _note, currentUid);
                        if (isInserted){
                            dialog.setMessage("Residents Register successfully!");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    id.setText("");
                                    name.setText("");
                                    contact.setText("");
                                    date.setText("");
                                    age.setText("");
                                    relative.setText("");
                                    note.setText("");
                                    radioGroup.clearCheck();
                                    InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                    mInputMethodManager.hideSoftInputFromWindow(ResidentsRegisterActivity.this.getCurrentFocus().getWindowToken(), 0);
                                }
                            });
                            dialog.show();
                        }
                        else
                            Toast.makeText(ResidentsRegisterActivity.this, "Residents Register Fail!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ResidentsRegisterActivity.this, "Residents cannot repeat.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // call date widget to select a date for an appointment
    public void showDatePickDlg () {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(ResidentsRegisterActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;//default month is from 0-11, need to add 1 to show the correct one.
                ResidentsRegisterActivity.this.date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}