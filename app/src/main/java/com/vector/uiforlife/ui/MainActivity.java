package com.vector.uiforlife.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vector.uiforlife.R;
import com.vector.uiforlife.fragment.ButtonFragment;

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
        @OnClick(R.id.list_view)
        void listViewClick(){
            startActivity(ListViewActivity.class);
        }
        @OnClick(R.id.show_fragment)
        void showFragmentClick(){
            startActivity(ShowFragmentActivity.class);
        }
        @OnClick(R.id.button_fragment)
        void buttonFragmentClick(){
            Intent intent = new Intent(MainActivity.this,ShowFragmentActivity.class);
            intent.putExtra("fragment","ButtonFragment");
            intent.putExtra("url","file:///android_asset/studyHtml/switch_button.html");
            startActivity(intent);
        }

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
