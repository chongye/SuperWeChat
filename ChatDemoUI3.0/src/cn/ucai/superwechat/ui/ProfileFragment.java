package cn.ucai.superwechat.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.Constant;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/4.
 */

public class ProfileFragment extends Fragment {
    Context mContext;
    View view;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_username)
    TextView tvUsername;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        initView();
    }

    private void initView() {
        String username = EMClient.getInstance().getCurrentUser();
        if (username != null) {
            tvUsername.setText(username);
            EaseUserUtils.setAppUserNick(username, tvNick);
            EaseUserUtils.setAppUserAvatar(mContext, username, ivAvatar);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        String username = EMClient.getInstance().getCurrentUser();
        EaseUserUtils.setAppUserNick(username, tvNick);
        EaseUserUtils.setAppUserAvatar(mContext, username, ivAvatar);
    }

    @OnClick({R.id.rl_profile, R.id.rl_setting, R.id.rl_money})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_profile:
                MFGT.gotoUserProfile(mContext);
                break;
            case R.id.rl_setting:
                MFGT.gotoSetting(mContext);
                break;
            //red packet code : 进入零钱页面
            case R.id.rl_money:
                RedPacketUtil.startChangeActivity(mContext);
                break;
            //end of red packet code
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (((MainActivity) getActivity()).isConflict) {
            outState.putBoolean("isConflict", true);
        } else if (((MainActivity) getActivity()).getCurrentAccountRemoved()) {
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }
}
