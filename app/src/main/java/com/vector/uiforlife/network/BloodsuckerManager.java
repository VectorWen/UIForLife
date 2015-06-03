package com.vector.uiforlife.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2015/6/3.
 */
public class BloodsuckerManager {
    public static RequestQueue mRequestQueue;

    public static synchronized void init(Context context){
        if(mRequestQueue == null){
            synchronized (BloodsuckerManager.class){
                if(mRequestQueue == null){
                    mRequestQueue = Volley.newRequestQueue(context);
                }
            }
        }
    }

    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
