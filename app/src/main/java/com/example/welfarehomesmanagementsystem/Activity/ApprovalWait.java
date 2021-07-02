package com.example.welfarehomesmanagementsystem.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.DbHelper_HealthCheck;
import com.example.welfarehomesmanagementsystem.DbHelper_Procurement;
import com.example.welfarehomesmanagementsystem.Entity.Approval;
import com.example.welfarehomesmanagementsystem.Entity.HealthCheck;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.ItemList;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ApprovalWait extends Fragment {
    List<Approval> waitList = new ArrayList<Approval>();
    private DbHelper_Procurement DB;
    private SharedPreferences pref;


    public static ApprovalWait newInstance() {
        return new ApprovalWait();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_approval_wait, container, false);

        DB = new DbHelper_Procurement(getActivity());
        pref= getActivity().getSharedPreferences("CurrentUserId",MODE_PRIVATE);
        String currentUid= pref.getString("currentUserId","");
        Cursor result=DB.getDataByUser(currentUid);
        while(result.moveToNext()){
            String _name = result.getString(0);
            int _amount = result.getInt(1);
            String _price = result.getString(2);
            String _staff = result.getString(3);
            String _manager = result.getString(4);
            String _date = result.getString(5);
            String _others = result.getString(6);
            String _status = result.getString(7);
            if(_status.equals("Waiting")) {
                Approval w = new Approval(_name, _date, _amount, _price, _manager, _staff, _others, _status);
                waitList.add(w);
            }
        }
        LinearLayout waiting = (LinearLayout) view.findViewById(R.id.approval_wait);

        if(waitList.isEmpty()){
            TextView note = view.findViewById(R.id.approval_wait_note);
            note.setVisibility(View.VISIBLE);
            note.setText("No procurement approval.");
        }
        else{
            for (Approval w : waitList) {
                ItemList showResult=new ItemList(getActivity(),null);
                showResult.setInfoContent1(w.getDate());
                showResult.setInfoContent2(w.getManager());
                showResult.setTitle(w.getItem());
                showResult.setInfo2("To manager: ");
                showResult.setLine(6,0xFF46A3FF);
                showResult.setTitleColor(0xFF46A3FF);
                waiting.addView(showResult);
            }
        }
        return view;
    }
}