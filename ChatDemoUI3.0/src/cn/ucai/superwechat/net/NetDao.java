package cn.ucai.superwechat.net;

import android.content.Context;

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
}
