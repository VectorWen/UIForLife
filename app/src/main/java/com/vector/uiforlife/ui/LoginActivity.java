package com.vector.uiforlife.ui;

import android.os.Bundle;
import android.view.View;

import com.vector.uiforlife.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
