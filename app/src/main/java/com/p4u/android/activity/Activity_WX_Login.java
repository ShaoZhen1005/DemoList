package com.p4u.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.p4u.android.R;
import com.p4u.android.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_WX_Login  extends Activity {

    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_login);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.setTitleName("微信登录");
        titleBar.setRightBtnVisable(false);
        titleBar.setOnTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
            }
        });
    }
    // 微信登录获取token和openid
    public static final String WX_TOKEN_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    // 微信登录获取用户信息
    public static final String WX_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
}
