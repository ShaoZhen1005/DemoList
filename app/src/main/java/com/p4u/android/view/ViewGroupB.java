package com.p4u.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.p4u.android.utils.LogUtils;

/**
 * Created by ShaoZhen-PC on 2017/9/21.
 */

public class ViewGroupB extends LinearLayout {
    private String TAG = "ViewGroupB";
    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.e(TAG, "事件分发 ViewGroupB dispatchTouchEventB");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.e(TAG, "事件拦截 ViewGroupB onInterceptTouchEventB");
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "事件消费 ViewGroupB onTouchEventB");
//        return true;
        return super.onTouchEvent(event);
    }
}
