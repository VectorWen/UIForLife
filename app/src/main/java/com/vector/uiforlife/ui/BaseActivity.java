package com.vector.uiforlife.ui;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2015/6/2.
 */
public class BaseActivity extends Activity {
    /**
     * 跳转到指定Activity 中
     * @param cls
     */
    public void startActivity(Class<?> cls){
        startActivity(new Intent(this,cls));
    }
}
