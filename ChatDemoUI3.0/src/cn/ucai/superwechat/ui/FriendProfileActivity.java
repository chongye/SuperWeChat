package cn.ucai.superwechat.ui;

import android.os.Bundle;
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
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        L.e(TAG,user.toString());
        if(user == null){
            MFGT.finish(this);
        }
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mComtext = this;
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.contact_detial_profile);
        //  getAppContactList 键为userName  传过来的好友信息，在ContactList里存在键，则为好友
        if(SuperWeChatHelper.getInstance().getAppContactList().containsKey(user.getMUserName())){
            btSendMsg.setVisibility(View.VISIBLE);
            btVideoChat.setVisibility(View.VISIBLE);
            EaseUserUtils.setAppUserNick(user.getMUserName(),tvNick);
            EaseUserUtils.setAppUserAvatar(mComtext,user.getMUserName(),ivAvatar);
            tvUsername.setText(user.getMUserName());
        }else{
            btAddContact.setVisibility(View.VISIBLE);
            EaseUserUtils.setAppContactAvatar(mComtext,user,ivAvatar);
            tvNick.setText(user.getMUserNick());
            tvUsername.setText(user.getMUserName());
        }
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
                break;
        }
    }
}
