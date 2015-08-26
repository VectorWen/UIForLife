package com.vector.uiforlife.bluetoothchat.display;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.google.common.base.Preconditions;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/24.
 */
public class AndroidDisplay implements Display {

    private final ActionBarActivity mActivity;

    public AndroidDisplay(ActionBarActivity activity) {
        mActivity = Preconditions.checkNotNull(activity, "activity cannot be null");
    }

    @Override
    public void showBluetoothChat() {

    }

    @Override
    public void showBluetoothList() {

    }

    @Override
    public void setActionBarTitle(CharSequence title) {
        ActionBar ab = mActivity.getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
    }
}
