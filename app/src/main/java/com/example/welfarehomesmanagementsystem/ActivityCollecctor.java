package com.example.welfarehomesmanagementsystem;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollecctor {
    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }
    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public static void finishAll()
    {
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        activities.clear();
    }
    public static Activity getCurrentActivity(){
        return activities.size()==0 ? null : activities.get(activities.size()-1);
    }
}
