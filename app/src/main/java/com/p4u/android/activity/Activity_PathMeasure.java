package com.p4u.android.activity;

import android.os.Bundle;
import android.view.View;

import com.p4u.android.R;
import com.p4u.android.view.GranzortView;
import com.p4u.android.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_PathMeasure extends Activity_Base {

    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Bind(R.id.view_granzort)
    GranzortView mGranzortView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_measure);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("PathMeasure");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
