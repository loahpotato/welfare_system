package com.example.welfarehomesmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.welfarehomesmanagementsystem.DbHelper_Procurement;
import com.example.welfarehomesmanagementsystem.Entity.Approval;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.ItemDetail;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

public class AllowApprovalActivity extends AppCompatActivity {
    private DbHelper_Procurement DB;
    private Approval w;
    private Button yes,no;
    private int rid;
    private String itemid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_approval);
        TitleLayout t=findViewById(R.id.title_approve);
        t.setT(R.string.approve);

        yes=findViewById(R.id.approve_submit);
        no=findViewById(R.id.not_approve_submit);
        DB=new DbHelper_Procurement(this);
        Intent intent =getIntent();
        rid=intent.getIntExtra("rid",0);
        itemid=intent.getStringExtra("itemId");
        Cursor result = DB.getByRowId(rid);

        while(result.moveToNext()){
            String _name = result.getString(0);
            int _amount = result.getInt(1);
            String _price = result.getString(2);
            String _staff = result.getString(3);
            String _manager = result.getString(4);
            String _date = result.getString(5);
            String _others = result.getString(6);
            String _status = result.getString(7);
            w = new Approval(_name, _date, _amount, _price, _manager, _staff, _others, _status);
        }
        LinearLayout detail=(LinearLayout) findViewById(R.id.approve_bg);
        ItemDetail show=new ItemDetail(this,null);
        show.setItem("Item: ",w.getItem());
        detail.addView(show);
        ItemDetail show1=new ItemDetail(this,null);
        show1.setItem("Date: ",w.getDate());
        detail.addView(show1);
        ItemDetail show2=new ItemDetail(this,null);
        show2.setItem("Amount: ", String.valueOf(w.getAmount()));
        detail.addView(show2);
        ItemDetail show3=new ItemDetail(this,null);
        show3.setItem("Unit price: ",w.getPrice());
        detail.addView(show3);
        ItemDetail show4=new ItemDetail(this,null);
        show4.setItem("From: ",w.getStaff());
        detail.addView(show4);
        ItemDetail show5=new ItemDetail(this,null);
        show5.setItem("Note: ",w.getOthers());
        detail.addView(show5);

        AlertDialog.Builder dialog = new AlertDialog.Builder(AllowApprovalActivity.this);
        dialog.setTitle("Approve result");
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.updateStatus(rid,"Approved");
                dialog.setMessage("Procurement approved.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent();
                        i.putExtra("returnApprove",itemid);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                });
                dialog.show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.updateStatus(rid,"Not Approved");
                dialog.setMessage("Procurement not be approved.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent();
                        i.putExtra("returnApprove",itemid);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                });
                dialog.show();
            }
        });

    }


}