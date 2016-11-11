package cn.ucai.superwechat.net;

import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.domain.Group;
import com.hyphenate.easeui.domain.User;

import java.io.File;

import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MD5;
import cn.ucai.superwechat.video.util.Utils;

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
    // 通过用户名查找用户
//    http://101.251.196.90:8000/SuperWeChatServerV2.0/findUserByUserName?m_user_name=yechong
    public static void findUserByUserName(Context context,String userName,OkHttpUtils.OnCompleteListener<String>listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME,userName)
                .targetClass(String.class)
                .execute(listener);
    }
    //  添加好友
    /*http://101.251.196.90:8000/SuperWeChatServerV2.0/addContact?m_contact_user_name=1&m_contact_cname=1*/
    public static void addContact(Context context,String userName,String cuserName,OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME,userName)
                .addParam(I.Contact.CU_NAME,cuserName)
                .targetClass(String.class)
                .execute(listener);
    }
    /*删除好友
    * http://101.251.196.90:8000/SuperWeChatServerV2.0/deleteContact?m_contact_user_name=1&m_contact_cname=1*/
    public static void deleteContact(Context context,String userName,String cuserName,OkHttpUtils.OnCompleteListener<String>listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME,userName)
                .addParam(I.Contact.CU_NAME,cuserName)
                .targetClass(String.class)
                .execute(listener);
    }
    /*下载全部好友
    * http://101.251.196.90:8000/SuperWeChatServerV2.0/downloadContactAllList?m_contact_user_name=yechong*/
    public static void downloadContactAllList(Context context,String userName,OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME,userName)
                .targetClass(String.class)
                .execute(listener);
    }
    /*创建群组
    http://101.251.196.90:8000/SuperWeChatServerV2.0/createGroup?m_group_hxid=1&m_group_name=2&m_group_description=1&m_group_owner=1&m_group_is_public=true&m_group_allow_invites=true*/
    public static void createGroup(Context context, EMGroup emGroup,OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam("m_group_hxid",emGroup.getGroupId())
                .addParam("m_group_name",emGroup.getGroupName())
                .addParam("m_group_description",emGroup.getDescription())
                .addParam("m_group_owner",emGroup.getOwner())
                .addParam("m_group_is_public",String.valueOf(emGroup.isPublic()))
                .addParam("m_group_allow_invites",String.valueOf(emGroup.isAllowInvites()))
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
    public static void createGroup(Context context,EMGroup emGroup,File file,OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam("m_group_hxid",emGroup.getGroupId())
                .addParam("m_group_name",emGroup.getGroupName())
                .addParam("m_group_description",emGroup.getDescription())
                .addParam("m_group_owner",emGroup.getOwner())
                .addParam("m_group_is_public",String.valueOf(emGroup.isPublic()))
                .addParam("m_group_allow_invites",String.valueOf(emGroup.isAllowInvites()))
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
    /*添加群成员
    * http://101.251.196.90:8000/SuperWeChatServerV2.0/addGroupMember?m_member_user_name=1&m_member_group_hxid=1*/
    public static void addGroupMember(Context context,EMGroup emGroup,OkHttpUtils.OnCompleteListener<String>listener){
        String member = null;
        for(String m : emGroup.getMembers()){
            if(!m.equals(EMClient.getInstance().getCurrentUser())){
                member += m + ",";
            }
        }
        member = member.substring(0,member.lastIndexOf(","));
        L.e("groupMember","group"+member.toString());
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_GROUP_MENBER)
                .addParam("m_member_user_name",member)
                .addParam("m_member_group_hxid",String.valueOf(emGroup.getGroupId()))
                .targetClass(String.class)
                .execute(listener);
    }
}
