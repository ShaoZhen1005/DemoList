package com.p4u.android.activity;

import android.os.Bundle;
import android.view.View;

import com.p4u.android.R;
import com.p4u.android.utils.LogUtils;
import com.p4u.android.view.Path_Hexagram_View;
import com.p4u.android.view.Path_Radar_View;
import com.p4u.android.view.Path_Search_View;
import com.p4u.android.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_Path_Search extends Activity_Base {

    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Bind(R.id.view_granzort)
    Path_Search_View mPath_Search_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_search);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("Path-搜索");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPath_Search_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(TAG,"setOnClickListener,view.getClass():" + v.getClass() + "");
            }
        });
        mPath_Search_View.setOnViewClick(new Path_Search_View.onViewClick() {
            @Override
            public void onClick(float scrollX, float scrollY) {
                LogUtils.e(TAG,"x轴移动了:" + scrollX + " px,y轴移动了:" + scrollY + " px");
            }
        });
    }

}
