package cn.ucai.superwechat.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class ExitAppActivity {
    // 设置为链表，有顺序
    List<Activity> mActivity = new LinkedList<>();
    private static ExitAppActivity instance = new ExitAppActivity();

    public ExitAppActivity(){

    }

    public static ExitAppActivity getInstance(){
        return instance;
    }
    public void addActivity(Activity activity){
        mActivity.add(activity);
    }
    public void delActivity(Activity activity){
        mActivity.remove(activity);
    }
    public void exit(){
        for(Activity activity:mActivity){
            activity.finish();
        }
    }
}
