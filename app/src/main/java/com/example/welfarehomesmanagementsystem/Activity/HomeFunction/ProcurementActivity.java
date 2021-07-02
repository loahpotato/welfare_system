package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.welfarehomesmanagementsystem.Activity.UpdateProfileActivity;
import com.example.welfarehomesmanagementsystem.DatabaseHelper;
import com.example.welfarehomesmanagementsystem.DbHelper_HealthCheck;
import com.example.welfarehomesmanagementsystem.DbHelper_Procurement;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

import java.util.Calendar;

public class ProcurementActivity extends AppCompatActivity {
    private RadioGroup rg1,rg2;
    private RadioButton wheelchair, oxygen, mask, crutch, nebulizer;
    private EditText amount, price, manager, date, note;
    private String item;
    Button submit;
    TextView chooseDate;
    String currentUid;
    private DbHelper_Procurement DB;
    private DatabaseHelper DB_check;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurement_approval);
        TitleLayout t = findViewById(R.id.title_procurement_approval);
        t.setT(R.string.procure);

        amount=findViewById(R.id.procure_amount);
        price=findViewById(R.id.procure_price);
        manager= findViewById(R.id.procure_manager);
        date=findViewById(R.id.procure_date);
        note=findViewById(R.id.procure_note);
        chooseDate = findViewById(R.id.procure_choose_date);
        submit = findViewById(R.id.procurement_submit);
        DB_check = new DatabaseHelper(this);
        DB = new DbHelper_Procurement(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        currentUid= pref.getString("currentUserId","");

        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        wheelchair=findViewById(R.id.wheelchair);
        oxygen=findViewById(R.id.oxygenerator);
        mask=findViewById((R.id.face_mask));
        crutch=findViewById(R.id.crutch);
        nebulizer=findViewById(R.id.nebulizer);

        rg1.clearCheck();
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDlg();
            }
        });
        AddData();
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2.clearCheck(); // clear the second RadioGroup!
                rg2.setOnCheckedChangeListener(listener2); //reset the listener
                if(wheelchair.getId()==checkedId)
                    item = (String)wheelchair.getText();
                if(oxygen.getId()==checkedId)
                    item = (String)oxygen.getText();
                if(mask.getId()==checkedId)
                    item = (String)mask.getText();
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
                if(crutch.getId()==checkedId)
                    item = (String)crutch.getText();
                if(nebulizer.getId()==checkedId)
                    item = (String)nebulizer.getText();
            }
        }
    };

    public void AddData(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _date=date.getText().toString();
                String _amount=amount.getText().toString();
                String _price=price.getText().toString();
                String _manager=manager.getText().toString();
                String _note=note.getText().toString();
                Cursor managerCheck = DB_check.getUserById(_manager);
                String manager_check_id = "";
                int manager_check = 0;
                while(managerCheck.moveToNext()){
                    manager_check_id = managerCheck.getString(0);
                    manager_check = managerCheck.getInt(3);
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProcurementActivity.this);
                dialog.setTitle("Approval Submission");

                if (item.equals("")||_amount.equals("")||_price.equals("")||_manager.equals("")||_date.equals("")) {
                    Toast.makeText(ProcurementActivity.this, "Please enter necessary fields", Toast.LENGTH_LONG).show();
                }
                else if(manager_check_id.equals("") || manager_check==0){
                    Toast.makeText(ProcurementActivity.this, "Manager ID does not exist!", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isInserted = DB.insertData(item, Integer.parseInt(_amount), _price, currentUid,_manager, _date, _note);
                    if (isInserted){
                        dialog.setMessage("Approval submission Successful.");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                date.setText("");
                                amount.setText("");
                                rg2.clearCheck();
                                rg1.clearCheck();
                                price.setText("");
                                manager.setText("");
                                note.setText("");
                                InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                mInputMethodManager.hideSoftInputFromWindow(ProcurementActivity.this.getCurrentFocus().getWindowToken(), 0);
                            }
                        });
                        dialog.show();
                    }
                    else
                        Toast.makeText(ProcurementActivity.this, "Fail!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void showDatePickDlg () {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(ProcurementActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;//default month is from 0-11, need to add 1 to show the correct one.
                ProcurementActivity.this.date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
