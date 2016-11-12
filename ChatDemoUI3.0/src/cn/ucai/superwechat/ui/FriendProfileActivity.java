package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatApplication;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.net.OkHttpUtils;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.ResultUtils;

public class FriendProfileActivity extends AppCompatActivity {
    final String TAG = FriendProfileActivity.class.getSimpleName();

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.bt_AddContact)
    Button btAddContact;
    @BindView(R.id.bt_sendMsg)
    Button btSendMsg;
    @BindView(R.id.bt_videoChat)
    Button btVideoChat;

    User user;
    FriendProfileActivity mComtext;
    String userName;
    boolean isFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        userName = getIntent().getStringExtra(I.User.USER_NAME);
        if(userName == null){
            MFGT.finish(this);
        }
        ButterKnife.bind(this);
        initView();
        if(SuperWeChatHelper.getInstance().getAppContactList().get(userName) == null){
            isFriend = false;
        }else {
            isFriend = true;
        }
        isFriend(isFriend);
        syncUserInfo();
    }

    private void syncUserInfo() {
        //  getAppContactList 键为userName  传过来的好友信息，在ContactList里存在键，则为好友
        NetDao.findUserByUserName(mComtext, userName, new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if(s!=null){
                    Result result = ResultUtils.getResultFromJson(s,User.class);
                    if(result!=null&&result.isRetMsg()){
                        User u = (User) result.getRetData();
                        if(u!=null){
                            L.e(TAG,"user:"+u.toString());
                            if(isFriend){
                                SuperWeChatHelper.getInstance().delAppContact(u.getMUserName());
                                SuperWeChatHelper.getInstance().saveAppContact(u);
                            }
                            user = u;
                            setUserInfo();
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initView() {
        mComtext = this;
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.contact_detial_profile);
    }
    public void isFriend(Boolean isFriend){
        if(isFriend){
            btSendMsg.setVisibility(View.VISIBLE);
            btVideoChat.setVisibility(View.VISIBLE);
        }else{
            btAddContact.setVisibility(View.VISIBLE);
        }
    }
    public void setUserInfo(){
        EaseUserUtils.setAppUserNick(user.getMUserName(),tvNick);
        EaseUserUtils.setAppContactAvatar(mComtext,user,ivAvatar);
        tvUsername.setText(user.getMUserName());
    }
    @OnClick({R.id.img_back, R.id.bt_AddContact, R.id.bt_sendMsg, R.id.bt_videoChat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(mComtext);
                break;
            //  添加好友，由环信推送
            case R.id.bt_AddContact:
                MFGT.gotoRequestAdd(mComtext,user);
                break;
            case R.id.bt_sendMsg:
                MFGT.gotoChat(mComtext,user.getMUserName());
                break;
            case R.id.bt_videoChat:
                startActivity(new Intent(this, VideoCallActivity.class).putExtra("username", user.getMUserName())
                        .putExtra("isComingCall", false));
                break;
        }
    }
}
