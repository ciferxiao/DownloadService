package com.example.xiaojinggong.downloadservice.Activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojinggong on 7/7/17.
 */

public class ActivityCollector {
    public static List<Activity> activityList=new ArrayList<>();
    public static void AddActivity(Activity activity){
        activityList.add(activity);
    }

    public static void RemoveActivity(Activity activity){
        activityList.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity:activityList){
                activity.finish();
        }
    }
}
