package com.p4u.android.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p4u.android.R;

/**
 * Created by ShaoZhen-PC on 2017/9/13.
 */

public class TitleBar extends RelativeLayout {


    public TextView title;
    public LinearLayout layoutLeft;
    public RelativeLayout layoutRight;
    public TextView leftTxt;
    public TextView righTxt;
    public ImageView back;
    public ImageView right;
    public TextView count;
    public View rightC;
    private boolean flag = false;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = (TextView) findViewById(R.id.title);
        layoutLeft = (LinearLayout) findViewById(R.id.layout_left);
        layoutRight = (RelativeLayout) findViewById(R.id.layout_right);
        back = (ImageView) findViewById(R.id.left);
        layoutLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).onBackPressed();
                }
            }
        });
        right = (ImageView) findViewById(R.id.right);
        count = (TextView) findViewById(R.id.count);
        rightC = findViewById(R.id.right_contain);
        leftTxt = (TextView) findViewById(R.id.left_txt);
        righTxt = (TextView) findViewById(R.id.right_txt);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    int w;

    public void init() {
        count.setVisibility(VISIBLE);
        if (w == 0) w = right.getWidth() / 2;
        if (!flag && w != 0) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) count.getLayoutParams();
            params.setMargins(w, 0, 0, 0);
            params.width = w;
            params.height = right.getHeight() * 26 / 72;
            count.setLayoutParams(params);
            flag = !flag;
        }
        postInvalidate();
    }

}