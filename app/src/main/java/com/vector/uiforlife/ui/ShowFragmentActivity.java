package com.vector.uiforlife.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.vector.uiforlife.fragment.WebFragment;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/7/30.
 */
public class ShowFragmentActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url","1234567890-=");
        webFragment.setArguments(bundle);
        setFragment(webFragment);
    }
    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(android.R.id.content,fragment).commit();
    }
}
