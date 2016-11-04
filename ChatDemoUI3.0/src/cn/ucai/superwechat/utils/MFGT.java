package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;

public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoMainActivity(Activity context){
        startActivity(context, MainActivity.class);
    }
    public static void startActivity(Context context,Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(context,cls);
        startActivity(context,intent);
    }
    public static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
    // 跳转到登录界面
    public static void gotoLoginActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(context,intent);
        finish((Activity) context);
    }
    // 跳转到注册界面
    public static void gotoRegisterActivity(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(context,intent);
        finish((Activity) context);
    }
    //  从个人中心跳转到用户设置界面
    public static void gotoSetting(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        startActivity(context,intent);
    }
    // 从个人中心跳转到用户资料
    public static void gotoUserProfile(Context context){
        Intent intent = new Intent(context, UserProfileActivity.class);
        startActivity(context,intent);
    }
}
