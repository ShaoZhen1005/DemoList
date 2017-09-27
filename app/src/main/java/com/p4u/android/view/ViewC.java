package com.p4u.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.p4u.android.utils.LogUtils;

/**
 * Created by ShaoZhen-PC on 2017/9/21.
 */

public class ViewC extends LinearLayout {
    private String TAG = "ViewC";

    public ViewC(Context context) {
        super(context);
    }

    public ViewC(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewC(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.e(TAG, "事件分发 ViewC dispatchTouchEventC");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "事件消费 ViewC onTouchEventC");
        return super.onTouchEvent(event);
    }
}
