package com.vector.uiforlife.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.vector.uiforlife.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DrawerLayoutActivity extends BaseActivity {


    @InjectView(R.id.drawer_content)
    FrameLayout mMainContent;
    @InjectView(R.id.drawer_menu)
    FrameLayout mDrawerMenu;
    @InjectView(R.id.drawer_layout)
    android.support.v4.widget.DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        ButterKnife.inject(this);
    }

}
