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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.widget.EaseAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.net.OkHttpUtils;
import cn.ucai.superwechat.utils.CommonUtils;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.ResultUtils;

public class AddContactActivity extends BaseActivity {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.et_SearchContact)
    EditText etSearchContact;

    private ProgressDialog progressDialog;

    AddContactActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_add_contact);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mContext = this;
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.add_friend);
        imgBack.setVisibility(View.VISIBLE);
        txtRight.setVisibility(View.VISIBLE);
    }


    /**
     * search contact
     * @param v
     *//*
    public void searchContact(View v) {
        final String name = editText.getText().toString();
        String saveText = searchBtn.getText().toString();

        if (getString(R.string.button_search).equals(saveText)) {
            toAddUsername = name;
            if (TextUtils.isEmpty(name)) {
                new EaseAlertDialog(this, R.string.Please_enter_a_username).show();
                return;
            }

            // TODO you can search the user from your app server here.

            //show the userame and add button if user exist
            searchedUserLayout.setVisibility(View.VISIBLE);
            nameText.setText(toAddUsername);

        }
    }

    *//**
     *  add contact
     * @param
     *//*
    public void addContact(View view) {
        if (EMClient.getInstance().getCurrentUser().equals(nameText.getText().toString())) {
            new EaseAlertDialog(this, R.string.not_add_myself).show();
            return;
        }

        if (SuperWeChatHelper.getInstance().getContactList().containsKey(nameText.getText().toString())) {
            //let the user know the contact already in your contact list
            if (EMClient.getInstance().contactManager().getBlackListUsernames().contains(nameText.getText().toString())) {
                new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
                return;
            }
            new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
            return;
        }

        progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {

                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String s = getResources().getString(R.string.Add_a_friend);
                    EMClient.getInstance().contactManager().addContact(toAddUsername, s);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }*/

    public void back(View v) {
        finish();
    }

    @OnClick({R.id.img_back, R.id.txt_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(mContext);
                break;
            case R.id.txt_right:
                searchContact();
                break;
        }
    }

    private void searchContact() {
        String contactName = etSearchContact.getText().toString().trim();
        String userName = EMClient.getInstance().getCurrentUser();
        if(TextUtils.isEmpty(contactName)){
            CommonUtils.showShortToast(R.string.contact_unable_empty);
            return;
        }else if(userName.equals(contactName)){
            CommonUtils.showShortToast(R.string.not_add_myself);
            return;
        }
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(R.string.addcontact_search);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        NetDao.findUserByUserName(mContext, contactName, new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if(s!=null){
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if(result!=null&&result.isRetMsg()){
                        User u = (User) result.getRetData();
                        MFGT.gotoFriendProfile(mContext,u.getMUserName());
                        progressDialog.dismiss();
                    }else {
                        CommonUtils.showShortToast(R.string.search_contact_fail);
                        progressDialog.dismiss();
                    }
                }else {
                    CommonUtils.showShortToast(R.string.search_contact_fail);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onError(String error) {
                CommonUtils.showShortToast(R.string.search_contact_fail);
                progressDialog.dismiss();
            }
        });
    }
}
