package com.vector.uiforlife.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vector.uiforlife.R;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: 显示一个html 页面，not js.
 * @date 2015/7/30.
 */
public class WebFragment extends Fragment {

    public static String errorHtml = "file:///android_asset/baseHtml/404.html";

    private String mUrl;
    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString("url");
        if(mUrl==null){
            mUrl = errorHtml;
        }else{
            Uri uri = Uri.parse(mUrl);
            if(uri.getHost()==null){
                mUrl = errorHtml;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mWebView = (WebView) inflater.inflate(R.layout.fragment_web_view,null,false);
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.loadUrl(mUrl);
        return mWebView;
    }

    private void onLoadError(){
        mWebView.loadUrl(errorHtml);
    }

    private class CustomWebViewClient extends WebViewClient{


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            onLoadError();
        }
    }

}
