package com.p4u.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p4u.android.R;

/**
 * Created by ShaoZhen-PC on 2017/9/13.
 */

public class TitleBar  extends RelativeLayout {

    private Button leftBtn; //左侧按钮控件
    private Button rightBtn;    //右侧按钮控件
    private TextView titleTv;   //标题文本控件
    public TitleBarClickListener listener;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    /**
     * 初始化参数，获取属性值
     * @param context 上下文
     */
    private void init(Context context){

        titleTv = new TextView(context);
        leftBtn = new Button(context);
        rightBtn = new Button(context);

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });

    }

    /**
     * 定义按钮点击接口，实现回调机制，通过映射的接口对象调用接口中的方法
     * 而不用去考虑如何实现，具体实现由调用者去创建
     */
    public interface TitleBarClickListener{
        void leftClick();   //左侧按钮点击事件
        void rightClick();  //右侧按钮点击事件
    }

    /**
     * 通过接口来获得回调者对接口的实现
     * @param listener
     */
    public void setOnTitleBarClickListener(TitleBarClickListener listener){
        this.listener = listener;
    }

    /**
     * 设置标题
     */
    public void setTitleName(String text){
        titleTv.setText(text);
    }
    public void setTitleName(int textSize,String text,int color){
        titleTv.setTextSize(textSize);
        titleTv.setText(text);
        titleTv.setTextColor(color);
    }

    /**
     * 设置左侧按钮是否可见
     * @param flag  是否可见
     */
    public void setLeftBtnVisable(boolean flag){
        if (flag){
            leftBtn.setVisibility(VISIBLE);
        }else {
            leftBtn.setVisibility(GONE);
        }
    }

    /**
     * 设置右侧按钮是否可见
     * @param flag 是否可见
     */
    public void setRightBtnVisable(boolean flag){
        if (flag){
            rightBtn.setVisibility(VISIBLE);
        }else {
            rightBtn.setVisibility(GONE);
        }
    }

}