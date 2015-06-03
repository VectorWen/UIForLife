package com.vector.uiforlife.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vector.uiforlife.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    private ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewHolder = new ViewHolder(getWindow().getDecorView());
    }

    /**
     */
    class ViewHolder {

        @OnClick(R.id.pinned_header_btn)
        void pinnedHeaderBtnClick(){
            startActivity(PinnedHeaderActy.class);
        }
        @OnClick(R.id.network_btn)
        void networkBtnClick(){
            startActivity(NetworkActivity.class);
        }

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
