package com.vector.uiforlife.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vector.uiforlife.R;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/28.
 */
public class DownloadManagerFragment extends Fragment {

    private Activity mContext;
    private DownloadManager mDownloadManager;

    private TextView mTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mDownloadManager = (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
        DownloadManager.getRecommendedMaxBytesOverMobile(mContext);
        DownloadManager.Query query = new DownloadManager.Query();
        Cursor cursor = mDownloadManager.query(query);
        int length;
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            length = cursor.getColumnCount();
            for(int i=0;i<length;i++){
                stringBuffer.append(cursor.getColumnName(i)).append("=").append(cursor.getString(i)).append("\n");
            }
        }
        View view  = inflater.inflate(R.layout.fragment_download_manager,null,false);
        mTxt = (TextView) view.findViewById(R.id.txt);

        stringBuffer.append(Environment.getDataDirectory()).append("\n");
        stringBuffer.append(Environment.getDownloadCacheDirectory()).append("\n");
        stringBuffer.append(Environment.getExternalStorageDirectory()).append("\n");
        stringBuffer.append(Environment.getExternalStoragePublicDirectory("txt")).append("\n");
        stringBuffer.append(Environment.getExternalStorageState()).append("\n");
        stringBuffer.append(Environment.getRootDirectory()).append("\n");
        stringBuffer.append(Environment.getRootDirectory()).append("\n");
        stringBuffer.append(Environment.isExternalStorageEmulated()).append("\n");


        mTxt.setText(stringBuffer.toString());

        NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        return view;
    }
}
