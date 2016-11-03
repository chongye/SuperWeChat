/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.superwechat.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class SettingsFragment extends Fragment {
    @BindView(R.id.txt_left)
    TextView txtLeft;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_username)
    TextView tvUsername;

    Context mContext;

    View view;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.em_fragment_conversation_settings, container, false);
        ButterKnife.bind(this, view);
        /*initView();*/
        return view;
    }

    private void initView() {
        txtLeft.setVisibility(View.VISIBLE);
        txtRight.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.rl_profile, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_profile:
                /*String username = EMClient.getInstance().getCurrentUser();
                if(username!=null){
                    tvUsername.setText(username);
                    EaseUserUtils.setAppUserNick(username, tvNick);
                    EaseUserUtils.setAppUserAvatar(mContext, username, ivAvatar);
                }*/
                break;
            case R.id.rl_setting:
                MFGT.gotoSetting(mContext);
                break;
        }
    }
}
