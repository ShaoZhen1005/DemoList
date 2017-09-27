package com.p4u.android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.p4u.android.R;
import com.p4u.android.utils.LogUtils;
import com.p4u.android.wx.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    public String TAG = this.getClass().getName();
    /**
     * 微信登录相关
     */
    private IWXAPI api;
    private Intent intent = new Intent("com.example.communication.RECEIVER");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this,Constants.WE_CHAT_APP_ID,true);
        //将应用的appid注册到微信
        api.registerApp(Constants.WE_CHAT_APP_ID);
        LogUtils.e(TAG,"------------------------------------");
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            if(!result){
                LogUtils.e(TAG,"参数不合法，未被SDK处理，退出");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtils.e(TAG,"baseReq:"+ JSON.toJSONString(baseReq));
    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtils.e(TAG,"baseResp:--A"+JSON.toJSONString(baseResp));
        LogUtils.e(TAG,"baseResp--B:"+baseResp.errStr+","+baseResp.openId+","+baseResp.transaction+","+baseResp.errCode);
        String result = "";
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result ="发送成功";
                //发送Action为com.example.communication.RECEIVER的广播
                intent.putExtra("baseJson", JSON.toJSONString(baseResp));
                sendBroadcast(intent);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                LogUtils.e(TAG,"发送取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                LogUtils.e(TAG,"发送被拒绝");
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                LogUtils.e(TAG,"签名错误");
                break;
            default:
                result = "发送返回";
//                showMsg(0,result);
                finish();
                break;
        }
        Toast.makeText(WXEntryActivity.this,result,Toast.LENGTH_LONG).show();

    }

    /**
     * 获取个人信息
     * @param accessTokenEntity
     */
//    private void getUserInfo(WXAccessTokenEntity accessTokenEntity) {
//        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
//        OkHttpUtils.get()
//                .url("https://api.weixin.qq.com/sns/userinfo")
//                .addParams("access_token",accessTokenEntity.getAccess_token())
//                .addParams("openid",accessTokenEntity.getOpenid())//openid:授权用户唯一标识
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(okhttp3.Call call, Exception e, int id) {
//                        ViseLog.d("获取错误..");
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        ViseLog.d("userInfo:"+response);
//                        WXUserInfo wxResponse = JSON.parseObject(response,WXUserInfo.class);
//                        ViseLog.d("微信登录资料已获取，后续未完成");
//                        String headUrl = wxResponse.getHeadimgurl();
//                        ViseLog.d("头像Url:"+headUrl);
//                        App.getShared().putString("headUrl",headUrl);
//                        Intent intent = getIntent();
//                        intent.putExtra("headUrl",headUrl);
//                        WXEntryActivity.this.setResult(0,intent);
//                        finish();
//                    }
//                });
//    }
}