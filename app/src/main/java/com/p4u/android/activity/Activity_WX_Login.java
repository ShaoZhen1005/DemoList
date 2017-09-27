package com.p4u.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.p4u.android.App;
import com.p4u.android.Http.ServerApi;
import com.p4u.android.R;
import com.p4u.android.URLs;
import com.p4u.android.bean.BeanWxLoginOpenid;
import com.p4u.android.bean.BeanWxLoginUserInfo;
import com.p4u.android.utils.LogUtils;
import com.p4u.android.view.TitleBar;
import com.p4u.android.wx.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_WX_Login extends Activity_Base implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private Intent mIntent;
    private MsgReceiver msgReceiver;

    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.wx_login)
    Button mWxLogin;

    @Bind(R.id.text_one_request)
    TextView mTextOneRequest;
    @Bind(R.id.text_two_request)
    TextView mTextTwoRequest;
    @Bind(R.id.text_three_request)
    TextView mTextThreeRequest;

    @Bind(R.id.image_wx_headimgurl)
    ImageView mImageWxHeadimgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_login);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("微信登录");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWXLogin();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_one_request:
                break;
            case R.id.text_two_request:
                break;
            case R.id.text_three_request:
                break;
        }

    }

    /**
     * 微信登陆
     */
    private void setWXLogin() {

        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.communication.RECEIVER");
        registerReceiver(msgReceiver, intentFilter);

        IWXAPI api = WXAPIFactory.createWXAPI(this, Constants.WE_CHAT_APP_ID, true);
        api.registerApp(Constants.WE_CHAT_APP_ID);
        LogUtils.e(TAG, "微信登录 准备调起微信 : " + (api != null) + " : " + isWeixinAvilible());
        if (api != null && isWeixinAvilible()) {
            Toast.makeText(mContext, "开始调起微信", Toast.LENGTH_SHORT).show();
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            Toast.makeText(mContext, "您尚未安装微信", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播
        closeBroadcast();
        // Activity销毁时，取消网络请求
        dispose();
    }

    /**
     * 关闭广播
     */
    private void closeBroadcast() {
        if (mIntent != null) {
            //停止服务
            stopService(mIntent);
            //注销广播
            unregisterReceiver(msgReceiver);
        }
    }

    /**
     * 广播接收器
     * "code":"061p0A9O1V98151lkG9O1epD9O1p0A9f",
     * "country":"CN",
     * "errCode":0,
     * "lang":"zh_CN",
     * "state":"wechat_sdk_demo_test",
     * "type":1,
     * "url":"wx6b1e001fd47936d9://oauth?code=061p0A9O1V98151lkG9O1epD9O1p0A9f&state=wechat_sdk_demo_test"
     */
    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到进度，更新UI
            String json = intent.getStringExtra("baseJson");
            LogUtils.e(TAG, "微信登录 返回json : " + json);
            JSONObject object = JSON.parseObject(json);
            findViewById(R.id.ll_one_request).setVisibility(View.VISIBLE);
            //利用键值对的方式获取到值
            String jsonStr = "{\r\n";
            jsonStr += String.format("\r\rcode : %s\r\n", object.get("code"));
            jsonStr += String.format("\r\rcountry : %s\r\n", object.get("country"));
            jsonStr += String.format("\r\rerrCode : %s\r\n", object.get("errCode"));
            jsonStr += String.format("\r\rlang : %s\r\n", object.get("lang"));
            jsonStr += String.format("\r\rstate : %s\r\n", object.get("state"));
            jsonStr += String.format("\r\rtype : %s\r\n", object.get("type"));
            jsonStr += String.format("\r\rurl : %s\r\n", object.get("url"));
            jsonStr += "}";
            mTextOneRequest.setText(jsonStr);
            closeBroadcast();
            requestOpenid(object.get("code")+"");
        }
    }

    /**
     * @param code
     *
     * 第二步：通过code获取access_token
     * 获取第一步的code后，请求以下链接获取
     * access_token：
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     * 参数说明
     * 参数	是否必须	说明
     * appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
     * secret	是	应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
     * code	是	填写第一步获取的code参数
     * grant_type	是	填authorization_code
     * 返回参数说明
     * access_token	接口调用凭证
     * expires_in	access_token  接口调用凭证超时时间，单位（秒）
     * refresh_token	用户刷新access_token
     * openid	授权用户唯一标识
     * scope	用户授权的作用域，使用逗号（,）分隔
     */
    public void requestOpenid(String code){
        OkGo.<String>post(URLs.WX_TOKEN_OPENID_URL)
                .tag(this)
                .params("appid", Constants.WE_CHAT_APP_ID)
                .params("secret", Constants.WE_APP_SECRET)
                .params("code", code)
                .params("grant_type", "authorization_code")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(TAG, "微信登录 response : " + response.body());
//                        requestUserInfo(object.get("openid")+"");
                        BeanWxLoginOpenid beanWxLoginOpenid = JSON.parseObject(response.body(),BeanWxLoginOpenid.class);

                        findViewById(R.id.ll_two_request).setVisibility(View.VISIBLE);
                        String jsonStr = "{\r\n";
                        jsonStr += String.format("\r\raccess_token : %s\r\n", beanWxLoginOpenid.getAccess_token());
                        jsonStr += String.format("\r\rexpires_in : %s\r\n", beanWxLoginOpenid.getExpires_in());
                        jsonStr += String.format("\r\rrefresh_token : %s\r\n", beanWxLoginOpenid.getRefresh_token());
                        jsonStr += String.format("\r\ropenid : %s\r\n", beanWxLoginOpenid.getOpenid());
                        jsonStr += String.format("\r\rscope : %s\r\n", beanWxLoginOpenid.getScope());
                        jsonStr += "}";
                        mTextTwoRequest.setText(jsonStr);
                        requestUserInfo(beanWxLoginOpenid.getAccess_token(),beanWxLoginOpenid.getOpenid());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * @param openid
     *
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
     * 参数说明
     * 参数	是否必须	说明
     * access_token	是	调用凭证
     * openid	是	普通用户的标识，对当前开发者帐号唯一
     * lang	  否	国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN
     * 返回参数说明
     * 参数	说明
     * openid	普通用户的标识，对当前开发者帐号唯一
     * nickname	普通用户昵称
     * sex	普通用户性别，1为男性，2为女性
     * province	普通用户个人资料填写的省份
     * city	普通用户个人资料填写的城市
     * country	国家，如中国为CN
     * headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     * privilege	用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
     * unionid	用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯
     */
    public void requestUserInfo(String access_token,String openid){
        OkGo.<String>post(URLs.WX_USER_INFO_URL)
                .tag(this)
                .params("access_token", access_token)
                .params("openid", openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(TAG, "微信登录 response : " + response.body());
                        BeanWxLoginUserInfo beanWxLoginUserInfo = JSON.parseObject(response.body(),BeanWxLoginUserInfo.class);
                        findViewById(R.id.ll_three_request).setVisibility(View.VISIBLE);
                        String jsonStr = "{\r\n";
                        jsonStr += String.format("\r\ropenid : %s\r\n", beanWxLoginUserInfo.getOpenid());
                        jsonStr += String.format("\r\rnickname : %s\r\n", beanWxLoginUserInfo.getNickname());
                        jsonStr += String.format("\r\rsex : %s\r\n", beanWxLoginUserInfo.getSex());
                        jsonStr += String.format("\r\rprovince : %s\r\n", beanWxLoginUserInfo.getProvince());
                        jsonStr += String.format("\r\rcity : %s\r\n", beanWxLoginUserInfo.getCity());
                        jsonStr += String.format("\r\rcountry : %s\r\n", beanWxLoginUserInfo.getCountry());
                        jsonStr += String.format("\r\rheadimgurl : %s\r\n", beanWxLoginUserInfo.getHeadimgurl());
                        jsonStr += String.format("\r\rprivilege : %s\r\n", beanWxLoginUserInfo.getPrivilege());
                        jsonStr += String.format("\r\runionid : %s\r\n", beanWxLoginUserInfo.getUnionid());
                        jsonStr += "}";
                        mTextThreeRequest.setText(jsonStr);
                        Glide.with(Activity_WX_Login.this).load(beanWxLoginUserInfo.getHeadimgurl()).into(mImageWxHeadimgurl);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    public boolean isWeixinAvilible() {
        final PackageManager packageManager = getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
