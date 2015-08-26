package com.vector.uiforlife.bluetoothchat.controllers;

import android.content.Intent;

import com.google.common.base.Preconditions;
import com.vector.uiforlife.bluetoothchat.display.Display;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/24.
 */
public class BaseController {
    private Display mDisplay;
    private boolean mInited;

    public final void init() {
        mInited = true;
        onInited();
    }

    public final void suspend() {
        onSuspended();
        mInited = false;
    }

    public final boolean isInited() {
        return mInited;
    }

    protected void onInited() {
    }

    protected void onSuspended() {
    }

    public boolean handleIntent(Intent intent) {
        return false;
    }

    protected void setDisplay(Display display) {
        mDisplay = display;
    }

    protected final Display getDisplay() {
        return mDisplay;
    }

    protected final void assertInited() {
        Preconditions.checkState(mInited, "Must be init'ed to perform action");
    }
}
