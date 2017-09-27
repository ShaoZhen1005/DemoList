package com.p4u.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.p4u.android.R;
import com.p4u.android.URLs;
import com.p4u.android.view.TitleBar;
import com.p4u.android.wx.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_OkGo extends Activity_Base {

    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okgo);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("OkGo网络请求");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.layoutRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okgo();
            }
        });
    }

    private void okgo(){
        ArrayList<String> stringArrayList = new ArrayList<>();
        ArrayList<File> fileArrayList = new ArrayList<>();
        List<HttpParams.FileWrapper> fileWrappers = new ArrayList<>();
//        OkGo.<String>post("url")
//                .tag(this)
//                .isMultipart(true)
//                .isSpliceUrl(true)
//                .retryCount(3)
//                .cacheKey("cacheKey")
//                .cacheTime(5000)
//                .cacheMode(CacheMode.DEFAULT)
//                .headers("headers1","headerValue1")
//                .headers("headers2","headerValue2")
//                .params("param1","paramValue1")
//                .params("param2","paramValue2")
//                .params("file1",new File("filepath1"))
//                .params("file2",new File("fliepath2"))
//                .addUrlParams("key", stringArrayList)
//                .addFileParams("key",fileArrayList)
//                .addFileWrapperParams("key",fileWrappers)
//                .execute(new JsonCa<String>(){
//
//                });

        /**
         第二步：通过code获取access_token
         获取第一步的code后，请求以下链接获取
         access_token：
         https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
         参数说明
         参数	是否必须	说明
         appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
         secret	是	应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
         code	是	填写第一步获取的code参数
         grant_type	是	填authorization_code
         */
        OkGo.<String>post(URLs.WX_TOKEN_OPENID_URL)
                .tag(this)
                .params("appid", Constants.WE_CHAT_APP_ID)
                .params("secret", Constants.WE_APP_SECRET)
                .params("param1","paramValue1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
