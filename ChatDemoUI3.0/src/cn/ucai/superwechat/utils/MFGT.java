package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.ChatActivity;
import cn.ucai.superwechat.ui.FriendProfileActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.NewFriendsMsgActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.RequestAddActivity;
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
    // 从个人中心跳转到用户详情
    public static void gotoUserProfile(Context context){
        Intent intent = new Intent(context, UserProfileActivity.class);
        startActivity(context,intent);
    }
    // 跳转到添加朋友界面
    public static void gotoAddFriend(Context context){
        Intent intent = new Intent(context, AddContactActivity.class);
        startActivity(context,intent);
    }
    //在查找成功，跳到好友信息界面
    public static void gotoFriendProfile(Context context,String username){
        Intent intent = new Intent(context, FriendProfileActivity.class);
        intent.putExtra(I.User.USER_NAME,username);
        startActivity(context,intent);
    }
    //跳转到请求添加好友界面
    public static void gotoRequestAdd(Context context,User user){
        Intent intent = new Intent(context, RequestAddActivity.class);
        intent.putExtra(I.User.USER_NAME,user);
        startActivity(context,intent);
    }
    // ;跳转到新朋友界面
    public static void gotoNewFriend(Context context){
        Intent intent = new Intent(context, NewFriendsMsgActivity.class);
        startActivity(context,intent);
    }
    //  跳转到聊天界面
    public static void gotoChat(Context context,String username){
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("userId",username);
        startActivity(context,intent);
    }

}
