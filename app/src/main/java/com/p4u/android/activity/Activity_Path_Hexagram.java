package com.p4u.android.activity;

import android.os.Bundle;
import android.view.View;

import com.p4u.android.R;
import com.p4u.android.view.GranzortView;
import com.p4u.android.view.Path_Hexagram_View;
import com.p4u.android.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_Path_Hexagram extends Activity_Base {

    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Bind(R.id.view_granzort)
    Path_Hexagram_View mPathHexagramView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_hexagram);
        // 绑定注解
        ButterKnife.bind(this);
        titleBar.title.setText("Path-六芒星");
        titleBar.back.setImageResource(R.mipmap.scriptlist_t_ic_back_default);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPath(){

    }

}
