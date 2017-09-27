package com.p4u.android.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.p4u.android.R;
import com.p4u.android.URLs;
import com.p4u.android.utils.LogUtils;
import com.p4u.android.utils.QQLoginUtil;
import com.p4u.android.view.TitleBar;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_QQ_Login extends Activity_Base implements View.OnClickListener {

    public static String mAppid = URLs.QQAppId;
    private Button mNewLoginButton;
    private Button mServerSideLoginBtn;
    private TextView mUserInfo;
    private ImageView mUserLogo;
    private UserInfo mInfo;
    public static Tencent mTencent;
    private static boolean isServerSideLogin = false;

    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_login);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("QQ登陆");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTencent = Tencent.createInstance(mAppid, mContext);
        initViews();
        updateLoginButton();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "-->onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "-->onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "-->onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "-->onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "-->onDestroy");
        super.onDestroy();

    }

    private void initViews() {
        mNewLoginButton = (Button) findViewById(R.id.new_login_btn);
        mServerSideLoginBtn = (Button) findViewById(R.id.server_side_login_btn);
        mUserInfo = (TextView) findViewById(R.id.user_nickname);
        mUserLogo = (ImageView) findViewById(R.id.user_logo);
        mNewLoginButton.setOnClickListener(this);
        mServerSideLoginBtn.setOnClickListener(this);
        updateLoginButton();
    }

    private void updateLoginButton() {
        if (mTencent != null && mTencent.isSessionValid()) {
            if (isServerSideLogin) {
                mNewLoginButton.setTextColor(Color.BLUE);
                mNewLoginButton.setText("登录");
                mServerSideLoginBtn.setTextColor(Color.RED);
                mServerSideLoginBtn.setText("退出Server-Side账号");
            } else {
                mNewLoginButton.setTextColor(Color.RED);
                mNewLoginButton.setText("退出帐号");
                mServerSideLoginBtn.setTextColor(Color.BLUE);
                mServerSideLoginBtn.setText("Server-Side登陆");
            }
        } else {
            mNewLoginButton.setTextColor(Color.BLUE);
            mNewLoginButton.setText("登录");
            mServerSideLoginBtn.setTextColor(Color.BLUE);
            mServerSideLoginBtn.setText("Server-Side登陆");
        }
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    new Thread() {

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject) response;
                            if (json.has("figureurl")) {
                                Bitmap bitmap = null;
                                try {
                                    bitmap = QQLoginUtil.getbitmap(json.getString("figureurl_qq_2"));
                                } catch (JSONException e) {

                                }
                                Message msg = new Message();
                                msg.obj = bitmap;
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {
            mUserInfo.setText("");
            mUserInfo.setVisibility(android.view.View.GONE);
            mUserLogo.setVisibility(android.view.View.GONE);
        }
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        mUserInfo.setVisibility(android.view.View.VISIBLE);
                        mUserInfo.setText(response.getString("nickname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (msg.what == 1) {
                Bitmap bitmap = (Bitmap) msg.obj;
                mUserLogo.setImageBitmap(bitmap);
                mUserLogo.setVisibility(android.view.View.VISIBLE);
            }
        }

    };

    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            LogUtils.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            updateUserInfo();
            updateLoginButton();
        }
    }

    private void onClickServerSideLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.loginServerSide(this, "all", loginListener);
            isServerSideLogin = true;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (!isServerSideLogin) { // SSO模式的登陆，先退出，再进行Server-Side模式登陆
                mTencent.logout(this);
                mTencent.loginServerSide(this, "all", loginListener);
                isServerSideLogin = true;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            isServerSideLogin = false;
            updateUserInfo();
            updateLoginButton();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
            updateLoginButton();
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                QQLoginUtil.showResultDialog(Activity_QQ_Login.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                QQLoginUtil.showResultDialog(Activity_QQ_Login.this, "返回为空", "登录失败");
                return;
            }
            QQLoginUtil.showResultDialog(Activity_QQ_Login.this, response.toString(), "登录成功");
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            QQLoginUtil.toastMessage(Activity_QQ_Login.this, "onError: " + e.errorDetail);
            QQLoginUtil.dismissDialog();
        }

        @Override
        public void onCancel() {
            QQLoginUtil.toastMessage(Activity_QQ_Login.this, "onCancel: ");
            QQLoginUtil.dismissDialog();
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Animation shake = AnimationUtils.loadAnimation(context,
                R.anim.shake);
        switch (v.getId()) {
            case R.id.new_login_btn: // 普通登陆
                onClickLogin();
                return;
            case R.id.server_side_login_btn:
                onClickServerSideLogin();
                return;
        }
        // 动画
        v.startAnimation(shake);
    }
}
