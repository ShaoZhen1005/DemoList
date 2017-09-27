package com.p4u.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.p4u.android.R;
import com.p4u.android.utils.LogUtils;
import com.p4u.android.view.Path_Radar_View;
import com.p4u.android.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_Incident_Distribute extends Activity_Base {

    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_distribute);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("Android事件分发");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * 核心要点
         * 1.事件分发原理: 责任链模式，事件层层传递，直到被消费。
         * 2.View 的 dispatchTouchEvent 主要用于调度自身的监听器和 onTouchEvent。
         * 3.View的事件的调度顺序是 onTouchListener > onTouchEvent > onLongClickListener > onClickListener 。
         * 4.不论 View 自身是否注册点击事件，只要 View 是可点击的就会消费事件。
         * 5.事件是否被消费由返回值决定，true 表示消费，false 表示不消费，与是否使用了事件无关。
         * 6.ViewGroup 中可能有多个 ChildView 时，将事件分配给包含点击位置的 ChildView。
         * 7.ViewGroup 和 ChildView 同时注册了事件监听器(onClick等)，由 ChildView 消费。
         * 8.一次触摸流程中产生事件应被同一 View 消费，全部接收或者全部拒绝。
         * 9.只要接受 ACTION_DOWN 就意味着接受所有的事件，拒绝 ACTION_DOWN 则不会收到后续内容。
         * 10.如果当前正在处理的事件被上层 View 拦截，会收到一个 ACTION_CANCEL，后续事件不会再传递过来。
         */
    }

}
