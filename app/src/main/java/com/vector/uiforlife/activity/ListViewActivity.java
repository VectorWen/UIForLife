package com.vector.uiforlife.activity;

import android.os.Bundle;

import com.vector.uiforlife.R;
import com.vector.uiforlife.fragment.LeftSlideFragment;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/7/29.
 */
public class ListViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getFragmentManager().beginTransaction().replace(R.id.main_fragment,new LeftSlideFragment()).commit();
    }
}
