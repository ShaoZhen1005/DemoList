package com.p4u.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.p4u.android.R;
import com.p4u.android.view.GranzortView;
import com.p4u.android.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_Fragment  extends Activity_Base {

    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Bind(R.id.view_granzort)
    GranzortView mViewGranzort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("Fragment+ViewPager");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
/**
 * activity与fragment通信
 * https://www.2cto.com/kf/201405/301679.html
 */
// activity端
//    @Override
//    public void onStart() {
//        Log.d(TAG, "-->onStart");
//        super.onStart();
//        // 向LoginFragment传递数据
//        LogUtils.e(TAG, "微信登录 由RegLoginActivity向LoginFragment传递数据");
//        getTitles();
//    }
//
//    public String getTitles(){
//        return "hello";
//    }
    // Fragment端
//@Override
//public void onAttach(Activity activity) {
//    super.onAttach(activity);
//    titles = ((MainActivity) activity).getTitles();//通过强转成宿主activity，就可以获取到传递过来的数据
//}
}
