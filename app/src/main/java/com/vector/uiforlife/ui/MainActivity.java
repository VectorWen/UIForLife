package com.vector.uiforlife.ui;

import android.os.Bundle;
import android.view.View;

import com.vector.uiforlife.R;

import butterknife.ButterKnife;
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
            startActivity(PinnedHeaderActivity.class);
        }
        @OnClick(R.id.network_btn)
        void networkBtnClick(){
            startActivity(NetworkActivity.class);
        }
        @OnClick(R.id.camera_btn)
        void cameraBtnClick(){
            startActivity(CameraActivity.class);
        }
        @OnClick(R.id.show_images_btn)
        void showImagesClick(){
            startActivity(ShowImagesActivity.class);
        }
        @OnClick(R.id.time_date_btn)
        void timeDateClick(){
            startActivity(TimeDateActivity.class);
        }
        @OnClick(R.id.widget)
        void widgetClick(){
            startActivity(WidgetActivity.class);
        }
        @OnClick(R.id.drawer_layout)
        void drawerLayoutClick(){
            startActivity(DrawerLayoutActivity.class);
        }

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
