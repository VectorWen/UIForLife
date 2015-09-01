package com.vector.uiforlife.activity;

import android.app.Activity;
import android.content.Intent;

import com.vector.uiforlife.network.Bloodsucker;

/**
 * Created by Administrator on 2015/6/2.
 */
public class BaseActivity extends Activity {

    //每一个Activity 都可以访问网络
    public Bloodsucker mBloodsucker = new Bloodsucker(this);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBloodsucker!=null){
            mBloodsucker.cancel();
        }
    }

    /**
     * 跳转到指定Activity 中
     * @param cls
     */
    public void startActivity(Class<?> cls){
        startActivity(new Intent(this,cls));
    }


}
