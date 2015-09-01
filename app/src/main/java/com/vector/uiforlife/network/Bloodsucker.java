package com.vector.uiforlife.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vector.uiforlife.entity.ResponseHeader;
import com.vector.uiforlife.activity.LoginActivity;
import com.vector.uiforlife.activity.NetworkActivity;


/**
 * Created by Administrator on 2015/6/3.
 */
public class Bloodsucker implements Response.Listener<String>,Response.ErrorListener{
    /**
     * 网络出错，无法链接网络
     */
    public static final int ERROR_CODE_INTERNET = 0;
    /**
     * 服务器出了问题
     */
    public static final int ERROR_CODE_SERVER = 1;

    private static RequestQueue mRequestQueue = BloodsuckerManager.getRequestQueue();
    private Context mContext;
    private StringRequest mStringRequest;
    private int mRequestCode;

    private BloodsuckerListener mBloodsuckerListener;
    private BloodsuckerErrorListener mErrorListener;

    public Bloodsucker(Context context){
        mContext = context;
    }

    public void get(int requestCode,String url,BloodsuckerListener l){
        get(requestCode,url,l,null);
    }
    public void get(int requestCode,String url,BloodsuckerListener l,BloodsuckerErrorListener errorListener){

        //一个实例对多只能发送一个请求
        if(mStringRequest != null && !mStringRequest.isCanceled()){
            mStringRequest.isCanceled();
        }
        mBloodsuckerListener = l;
        mErrorListener = errorListener;
        mRequestCode = requestCode;
        mStringRequest = new StringRequest(Request.Method.GET,url,this,this);
        mRequestQueue.add(mStringRequest);
    }

    /**
     * 因为这个是和应用程序绑定的，所以在Activity，Fragment 死掉需要自己处理自己的请求
     */
    public void cancel(){
        if(mStringRequest != null && !mStringRequest.isCanceled()){
            mStringRequest.isCanceled();
            mStringRequest = null;
        }
    }

    @Override
    public void onResponse(String json) {

        json = NetworkActivity.test;
        Log.i("Bloodsucker",json);

        ResponseHeader responseHeader = ResponseHeader.createInstanceByJson(json);
        if(responseHeader == null){
            //返回的数据不是json 格式
            Toast.makeText(mContext,"服务器维护中...",Toast.LENGTH_SHORT).show();
            if(mErrorListener!=null)
            mErrorListener.error(mRequestCode,ERROR_CODE_SERVER);
            return;
        }
        //到了下面就是服务器返回的正常数据

        switch (responseHeader.getCode()){
            case 200: //请求成功背服务器处理
                if(mBloodsuckerListener!=null)
                mBloodsuckerListener.ok(mRequestCode,json);
                break;
            case 201: //not login
                mContext.startActivity(new Intent(mContext,LoginActivity.class));
                break;
            case 404:
            case 505:
            case 606:
            default: //其他错误
                Toast.makeText(mContext,responseHeader.getMsg()+responseHeader.getCode(),Toast.LENGTH_SHORT).show();
                if(mErrorListener!=null)
                    mErrorListener.error(mRequestCode,responseHeader.getCode());
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Toast.makeText(mContext,"网络出错,请检查你的网络是否正常连接",Toast.LENGTH_SHORT).show();
        if(mErrorListener!=null)
        mErrorListener.error(mRequestCode,ERROR_CODE_INTERNET);
    }

    public interface BloodsuckerListener<T>{
        /**
         * 成功拿到能显示的数据，没错，就是可以显示了，错误的可能性全部处理了
         * @param requestCode 请求代码
         * @param json 拿到的响应对象
         */
        public void ok(int requestCode,String json);
    }

    public interface BloodsuckerErrorListener{
        /**
         * 全部错误都来到这里
         * @param requestCode
         * @param errorCode
         */
        public void error(int requestCode,int errorCode);
    }

}
