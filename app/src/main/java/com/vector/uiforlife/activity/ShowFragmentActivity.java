package com.vector.uiforlife.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vector.uiforlife.R;
import com.vector.uiforlife.download.DownloadManagerFragment;
import com.vector.uiforlife.fragment.ButtonFragment;
import com.vector.uiforlife.fragment.EditTextFragment;
import com.vector.uiforlife.fragment.NavigationFragment;
import com.vector.uiforlife.fragment.WebFragment;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/7/30.
 */
public class ShowFragmentActivity extends Activity{

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String fragmentName = getIntent().getStringExtra("fragment");
        mUrl = getIntent().getStringExtra("url");

        switch (fragmentName){
            case "ButtonFragment":
                ButtonFragment buttonFragment = new ButtonFragment();
                setFragment(buttonFragment);
                break;
            case "NavigationFragment":
                NavigationFragment navigationFragment = new NavigationFragment();
                setFragment(navigationFragment);
                break;
            case "DownloadManagerFragment":
                DownloadManagerFragment downloadManagerFragment = new DownloadManagerFragment();
                setFragment(downloadManagerFragment);
                break;
            case "EditTextFragment":
                EditTextFragment editTextFragment = new EditTextFragment();
                setFragment(editTextFragment);
                break;
            default:
                startWebFragment(mUrl);
                break;
        }
    }

    private void startWebFragment(String url){
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        webFragment.setArguments(bundle);
        setFragment(webFragment);
    }

    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(android.R.id.content,fragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_study) {
            startWebFragment(mUrl);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
