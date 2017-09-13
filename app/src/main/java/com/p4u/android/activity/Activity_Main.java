package com.p4u.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.p4u.android.R;
import com.p4u.android.view.TitleBar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Activity_Main extends Activity_Base {
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.title_bar)
    TitleBar titleBar;

    private Context mContext;

    private String[] buttonName = new String[]{"微信登录", "QQ登陆", "Fragment+ViewPager"};
    private HashMap<String, Intent> activityHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 绑定注解
        ButterKnife.bind(this);

        mContext = getApplicationContext();
        initAct();
        initView();
        setContentView(llMain);

    }

    private void initAct() {
        activityHashMap.put("微信登录", new Intent(this, Activity_WX_Login.class));
        activityHashMap.put("QQ登陆", new Intent(this, Activity_QQ_Login.class));
        activityHashMap.put("Fragment+ViewPager", new Intent(this, Activity_Fragment.class));
    }

    public void initView() {
        titleBar.setTitleName("主页");
        titleBar.setLeftBtnVisable(false);
        titleBar.setRightBtnVisable(true);
        titleBar.setOnTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(getApplicationContext(),"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
            }
        });

        ScrollView scrollView = new ScrollView(mContext);
        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setPadding(0,5,0,5);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout,layoutParams);
        llMain.addView(scrollView,layoutParams);
        //获取key和value的set
        Iterator iter = activityHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            //把hashmap转成Iterator再迭代到entry
            Map.Entry entry = (Map.Entry) iter.next();
            //从entry获取key
            String key = (String) entry.getKey();
            //从entry获取value
            Intent val = (Intent) entry.getValue();
            addButton(linearLayout,key, val);
        }
    }

    private void addButton(LinearLayout ll,String name, final Intent intent) {
        RelativeLayout rl = (RelativeLayout) View.inflate(
                getApplicationContext(), R.layout.include_main_button, null);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        TextView tv = (TextView) rl.findViewById(R.id.include_button_style);
        tv.setText(name);
        ll.addView(rl);
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
