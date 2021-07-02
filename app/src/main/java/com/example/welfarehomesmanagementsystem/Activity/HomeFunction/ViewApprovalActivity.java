package com.example.welfarehomesmanagementsystem.Activity.HomeFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.Activity.AllowApprovalActivity;
import com.example.welfarehomesmanagementsystem.DbHelper_Procurement;
import com.example.welfarehomesmanagementsystem.Entity.Approval;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.ItemList;
import com.example.welfarehomesmanagementsystem.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewApprovalActivity extends AppCompatActivity {
    List<Approval> viewList = new ArrayList<Approval>();
    List<ItemList> allItem = new ArrayList<ItemList>();
    private LinearLayout viewApproval ;
    private DbHelper_Procurement DB;
    private SharedPreferences pref;
    private TextView note;
    private int itemId  =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_approval);
        TitleLayout t=findViewById(R.id.title_view_approval);
        t.setT(R.string.view_approval);
        note = findViewById(R.id.view_approval_note);
        viewApproval = (LinearLayout) findViewById(R.id.layout_view_approval);
        DB = new DbHelper_Procurement(this);
        pref= getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String currentUid= pref.getString("currentUserId","");
        Cursor result=DB.getDataByManager(currentUid);

        while(result.moveToNext()){
            int _rid=result.getInt(0);
            String _name = result.getString(1);
            int _amount = result.getInt(2);
            String _price = result.getString(3);
            String _staff = result.getString(4);
            String _manager = result.getString(5);
            String _date = result.getString(6);
            String _others = result.getString(7);
            String _status = result.getString(8);
            if(_status.equals("Waiting")) {
                Approval w = new Approval(_name, _date, _amount, _price, _manager, _staff, _others, _status);
                w.setRid(_rid);
                viewList.add(w);
            }
        }

        if(viewList.isEmpty()){
            note.setVisibility(View.VISIBLE);
            note.setText("No procurement waiting to approve.");
        }
        else{

            for (Approval w : viewList) {
                ItemList showResult=new ItemList(this,null);
                showResult.setInfoContent1(w.getDate());
                showResult.setInfoContent2(w.getStaff());
                showResult.setTitle(w.getItem());
                showResult.setInfo2("From:  ");
                showResult.setLine(6,0xFF46A3FF);
                showResult.setTitleColor(0xFF46A3FF);
                viewApproval.addView(showResult);
                showResult.setTag(itemId);
                allItem.add(showResult);
                showResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent(ViewApprovalActivity.this, AllowApprovalActivity.class);
                        intent.putExtra("rid", w.getRid());
                        intent.putExtra("itemId", String.valueOf(showResult.getTag()));
                        startActivityForResult(intent,1);
                    }
                });
                itemId++;

            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    int i= Integer.parseInt(data.getStringExtra("returnApprove"));
                    viewApproval.removeView(allItem.get(i));
                    //allItem.remove(i);
                    itemId--;
                    if(itemId==0)
                    {
                        note.setVisibility(View.VISIBLE);
                        note.setText("No procurement waiting to approve.");
                    }
                }
                break;
            default:
                break;
        }
    }
}