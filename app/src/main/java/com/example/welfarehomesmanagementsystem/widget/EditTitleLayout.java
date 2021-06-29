package com.example.welfarehomesmanagementsystem.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.welfarehomesmanagementsystem.Activity.ProfileActivity;
import com.example.welfarehomesmanagementsystem.R;
import com.example.welfarehomesmanagementsystem.Activity.Edit.EditName;
import com.example.welfarehomesmanagementsystem.ActivityCollecctor;

public class EditTitleLayout extends LinearLayout {
        private ImageView iv_backward;
        private TextView tv_title, tv_forward;

        public EditTitleLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            LinearLayout edit_title = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.edit_title, this);
            iv_backward = (ImageView) edit_title.findViewById(R.id.iv_backward);
            tv_title = (TextView) edit_title.findViewById(R.id.tv_title);
            tv_forward = (TextView) edit_title.findViewById(R.id.tv_forward);
            if(ActivityCollecctor.getCurrentActivity().getClass().equals(EditName.class)){
                tv_forward.setText("Confirm");
                tv_title.setText("Edit Your Name");
            }

            //设置监听器
            //如果点击back则结束活动
            iv_backward.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity)getContext()).finish();
                }
            });
        }
        public TextView getTextView_forward(){
            return tv_forward;
        }
}
