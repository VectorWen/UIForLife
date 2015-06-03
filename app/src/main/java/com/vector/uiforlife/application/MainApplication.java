package com.vector.uiforlife.application;

import android.app.Application;

import com.vector.uiforlife.network.BloodsuckerManager;

/**
 * Created by Administrator on 2015/6/3.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BloodsuckerManager.init(this); //初始化网络访问

    }
}
