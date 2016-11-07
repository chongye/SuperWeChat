package cn.ucai.superwechat.net;

import android.content.Context;

import java.io.File;

import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.MD5;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NetDao {
    // 账号注册
    public static void register(Context context,String username, String nick, String password, OkHttpUtils.OnCompleteListener<Result>listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(Result.class)
                .post()
                .execute(listener);
    }
    //  取消注册
    public static void unregister(Context context,String username,OkHttpUtils.OnCompleteListener<Result>listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME,username)
                .targetClass(Result.class)
                .execute(listener);
    }
    // 用户登录
    public static void login(Context context,String userName,String password,OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.User.PASSWORD,MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }
    //  更新用户昵称
    public static void updateNick(Context context,String userName,String nick,OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.User.NICK,nick)
                .targetClass(String.class)
                .execute(listener);
    }
    // 更新用户图像
    // http://101.251.196.90:8000/SuperWeChatServerV2.0/updateAvatar?name_or_hxid=yechong&avatarType=user_avatar
    public static void updateAvatar(Context context, String userName, File file, OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID,userName)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }
}
