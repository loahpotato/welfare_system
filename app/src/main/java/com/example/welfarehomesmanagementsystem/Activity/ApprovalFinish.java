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

import com.example.welfarehomesmanagementsystem.DbHelper_Procurement;
import com.example.welfarehomesmanagementsystem.Entity.Approval;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.widget.ItemList;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ApprovalFinish extends Fragment {
    List<Approval> finishList = new ArrayList<Approval>();
    private DbHelper_Procurement DB;
    private SharedPreferences pref;


    public static ApprovalFinish newInstance() {
        return new ApprovalFinish();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_approval_finish, container, false);

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
            if(!_status.equals("Waiting")) {
                Approval w = new Approval(_name, _date, _amount, _price, _manager, _staff, _others, _status);
                finishList.add(w);
            }
        }
        LinearLayout finish = (LinearLayout) view.findViewById(R.id.approval_finish);

        if(finishList.isEmpty()){
            TextView note = view.findViewById(R.id.approval_finish_note);
            note.setVisibility(View.VISIBLE);
            note.setText("No procurement approval.");
        }
        else{
            for (Approval w : finishList) {
                ItemList showResult=new ItemList(getActivity(),null);
                showResult.setInfoContent1(w.getDate());
                showResult.setInfoContent2(w.getManager());
                showResult.setTitle(w.getItem());
                showResult.setInfo2("To manager: ");
                if(w.getStatus().equals("Not Approved")) {
                    showResult.setLine(6,0xFFDA3434);
                    showResult.setTitleColor(0xFFDA3434);
                    showResult.setNote("Not Approved");
                }
                else{
                    showResult.setLine(6,0xFF069A7C);
                    showResult.setTitleColor(0xFF069A7C);
                    showResult.setNote("Approved");
                }
                finish.addView(showResult);
            }
        }
        return view;
    }
}