package com.p4u.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p4u.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Activity_Main extends Activity {
    private final String TAG = this.getClass().getName();
    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private String[] buttonName = new String[]{"微信登录", "QQ登陆", "Fragment+ViewPager"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 绑定注解
        ButterKnife.bind(this);
        initView();
        setContentView(llMain);
    }

    public void initView() {
//        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        for (int i = 0; i < buttonName.length; i++) {
            addButton(i);
        }
    }

    private void addButton(int id) {
        RelativeLayout rl = (RelativeLayout) View.inflate(
                getApplicationContext(), R.layout.include_main_button, null);
        TextView tv = (TextView) rl.findViewById(R.id.include_button_style);
        tv.setText(buttonName[id]);
        llMain.addView(rl);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "-->onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "-->onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "-->onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "-->onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "-->onDestroy");
        super.onDestroy();
    }

    //    class AccessNetwork implements Runnable {
//        private String op;
//        private String url;
//        private String params;
//
//        public AccessNetwork(String op, String url, String params) {
//            super();
//            this.op = op;
//            this.url = url;
//            this.params = params;
//        }
//
//        @Override
//        public void run() {
//            LogUtils.i(TAG, "设备号:" + mDeviceid);
//            if (op.equals("GET")) {
//                LogUtils.i(TAG, "发送GET请求");
//                jsonString = GetPostUtil.sendGet(url, params);
//                LogUtils.i(TAG, ">>>>>>>>>>>>" + jsonString);
//            }
//            if (op.equals("POST")) {
//                LogUtils.i(TAG, "发送POST请求");
//                jsonString = GetPostUtil.sendPost(url, params);
//                LogUtils.i(TAG, ">>>>>>>>>>>>" + jsonString);
//            }
//            if (op.equals("GET-UTF-8")) {
//                try {
//                    // 参数拼接在urlPath中
//                    URL url = new URL(this.url + "?" + params);
//                    URLConnection ucon = url.openConnection();
//
//                    // 使用InputStream，从URLConnection读取数据
//                    InputStream is = ucon.getInputStream();
//                    BufferedInputStream bis = new BufferedInputStream(is);
//                    // 用ByteArrayBuffer缓存
//                    ByteArrayBuffer baf = new ByteArrayBuffer(1024);
//                    int current = 0;
//                    while ((current = bis.read()) != -1) {
//                        baf.append((byte) current);
//                    }
//                    // 将缓存的内容转化为String,用UTF-8编码
//                    jsonString = EncodingUtils.getString(baf.toByteArray(),
//                            "UTF-8");
//                } catch (Exception e) {
//                    LogUtils.i(TAG, "error");
//                }
//            }
//
//            Runnable payRunnable = new Runnable() {
//
//                @Override
//                public void run() {
//                    LogUtils.i(TAG, "服务器返回:" + jsonString);
//                    PayTask payTask = new PayTask(Activity_Award.this);
//                    String payString = payTask.pay(jsonString);
//                    LogUtils.i(TAG, "支付宝返回:" + payString);
//
//                    Message msg = new Message();
//                    msg.what = SDK_PAY_FLAG;
//                    msg.obj = payString;
//                    mHandler.sendMessage(msg);
//                }
//            };
//
//            // 必须异步调用
//            Thread payThread = new Thread(payRunnable);
//            payThread.start();
//
//        }
//    }

}
